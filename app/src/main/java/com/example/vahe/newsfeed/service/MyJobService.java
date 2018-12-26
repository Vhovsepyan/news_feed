package com.example.vahe.newsfeed.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.vahe.newsfeed.NewsFeedApp;
import com.example.vahe.newsfeed.R;
import com.example.vahe.newsfeed.listener.OnListReadyListener;
import com.example.vahe.newsfeed.model.Article;
import com.example.vahe.newsfeed.repository.ArticleRepository;
import com.example.vahe.newsfeed.view.MainActivity;
import com.example.vahe.newsfeed.utils.AppLog;
import com.example.vahe.newsfeed.utils.ArticleUrlBuilder;
import com.example.vahe.newsfeed.utils.Constants;
import com.example.vahe.newsfeed.utils.SharedPrefs;

import java.util.List;

import javax.inject.Inject;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final String CHANNEL_ID = "NEWS_CHANNEL";
    private String lastUpdateDate = "";

    @Inject
    public ArticleRepository repository;

    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        NewsFeedApp app = (NewsFeedApp) getApplication();
        app.appComponent().inject(this);
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        handler.postDelayed(() -> {
            AppLog.i(" onStartJob ");
            lastUpdateDate = SharedPrefs.getInstance()
                    .getString(Constants.PREF_LAST_PUBLICATION_DATE, "");
            String url = new ArticleUrlBuilder()
                    .addUseDate(Constants.USE_DATE_PUBLISHED)
                    .addOrderBy(Constants.ORDER_BY_NEWEST)
                    .addOrderDate(Constants.ORDER_DATE_PUBLISHED)
                    .addFromDate(lastUpdateDate)
                    .build();
            repository.getArticlesFromApi(url, listReadyListener);
        }, 30000);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    private OnListReadyListener listReadyListener = new OnListReadyListener() {
        @Override
        public void onReady(List list) {
            if (list != null && !list.isEmpty()) {
                Article lastItem = (Article) list.get(0);
                AppLog.i(" checkForNewArticles tempArticles.size == " + list.size());
                if (!lastUpdateDate.equalsIgnoreCase(lastItem.getWebPublicationDate())) {
                    createNotification(lastItem);
                }
            }
        }
    };

    private void createNotification(Article article) {
// Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        createNotificationChannel();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(article.getSectionName())
                .setContentText(article.getWebTitle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1112, mBuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}

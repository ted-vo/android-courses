![CorssTech](../images/ic-cross-tech.png "Activity lifecycle")

# Android Components

## Activities

Original docs: [Android Developer Activity Docs](https://developer.android.com/reference/kotlin/android/app/Activity "Activity")

> They dictate the UI and handle the user interaction to the smart phone screen.

- An activity represents a single screen with a user interface,in-short Activity performs actions on the screen. For example, an email application might have one activity that shows a list of new emails, another activity to compose an email, and another activity for reading emails. If an application has more than one activity, then one of them should be marked as the activity that is presented when the application is launched.

``` Java
# Java
public class MainActivity extends Activity {}
```

``` Kotlin
# Koltin
class MainActivity : Activity {}
```

## Services

Original docs: [Android Developer Service Docs](https://developer.android.com/reference/kotlin/android/app/Service "Services")

> They handle background processing associated with an application.

- A service is a component that runs in the background to perform long-running operations. For example, a service might play music in the background while the user is in a different application, or it might fetch data over the network without blocking user interaction with an activity.

``` Java
# Java
public class MyService extends Service {}
```

``` Kotlin
# Kotlin
class MyService : Service {}
```

<P style="page-break-before: always">

![CorssTech](../images/cross-tech-logo.png "Activity lifecycle")

## Broadcast Receivers

Original docs: [Android Developer BroadcastReceiver Docs](https://developer.android.com/reference/kotlin/android/content/BroadcastReceiver.html "BroadcastReceiver")

> They handle communication between Android OS and applications.

- Broadcast Receivers simply respond to broadcast messages from other applications or from the system. For example, applications can also initiate broadcasts to let other applications know that some data has been downloaded to the device and is available for them to use, so this is broadcast receiver who will intercept this communication and will initiate appropriate action.

- A broadcast receiver is implemented as a subclass of BroadcastReceiver class and each message is broadcaster as an Intent object.

``` Java
# Java
public class MyReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {}
}
```

``` Kotlin
# Kotlin
class MyReceiver : BroadcastReceiver {
  override fun onReceive(context: Context?, intent: Intent?) {}
}
```

<P style="page-break-before: always">

![CorssTech](../images/cross-tech-logo.png "Activity lifecycle")

## Content Providers

Original docs: [Android Developer Content-Provider Docs](https://developer.android.com/reference/kotlin/android/content/ContentProvider.html "Content-Provider")

> They handle data and database management issues.

- A content provider component supplies data from one application to others on request. Such requests are handled by the methods of the ContentResolver class. The data may be stored in the file system, the database or somewhere else entirely.

- A content provider is implemented as a subclass of ContentProvider class and must implement a standard set of APIs that enable other applications to perform transactions.

```Java
# Java
public class MyContentProvider extends ContentProvider {
  @Override
  public boolean onCreate() {}

  @Nullable
  @Override
  public Cursor query(...) {}

  @Nullable
  @Override
  public String getType(...) {}

  @Nullable
  @Override
  public Uri insert(...) {}

  @Override
  public int delete(...) {}

  @Override
  public int update(...) {}
}
```

``` Kotlin
# Kotlin
class MyContentProvider : ContentProvider() {
  override fun onCreate(): Boolean {}

  override fun query(...): Cursor {}

  override fun getType(...): String {}

  override fun insert(...): Uri {}

  override fun delete(...): Int {}

  override fun update(...): Int {}
}
```

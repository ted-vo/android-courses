![CorssTech](../../../assets/ic-cross-tech.png "CrossTech")

# Event Handling

> Events are a useful way to collect data about a user's interaction with interactive components of Applications. Like button presses or screen touch etc. The Android framework maintains an event queue as first-in, first-out (FIFO) basis. You can capture these events in your program and take appropriate action as per requirements.

There are following three concepts related to Android Event Management

- Event Listeners − An event listener is an interface in the View class that contains a single callback method. These methods will be called by the Android framework when the View to which the listener has been registered is triggered by user interaction with the item in the UI.

- Event Listeners Registration − Event Registration is the process by which an Event Handler gets registered with an Event Listener so that the handler is called when the Event Listener fires the event.

- Event Handlers − When an event happens and we have registered an event listener for the event, the event listener calls the Event Handlers, which is the method that actually handles the event.

## Event Listeners & Event Handlers

| Event Handler | Event Listener | Description |
| ------------- | -------------- | ----------- |
| onClick() | **OnClickListener()** | This is called when the user either clicks or touches or focuses upon any widget like button, text, image etc. You will use onClick() event handler to handle such event. |
| onLongClick() | **OnLongClickListener()** | This is called when the user either clicks or touches or focuses upon any widget like button, text, image etc. for one or more seconds. You will use onLongClick() event handler to handle such event. |
| onFocusChange() | **OnFocusChangeListener()** | This is called when the widget looses its focus ie. user goes away from the view item. You will use onFocusChange() event handler to handle such event. |
| onKey() | **OnFocusChangeListener()** | This is called when the user is focused on the item and presses or releases a hardware key on the device. You will use onKey() event handler to handle such event. |
| onTouch() | **OnTouchListener()** | This is called when the user presses the key, releases the key, or any movement gesture on the screen. You will use onTouch() event handler to handle such event. |
| onMenuItemClick() | **OnMenuItemClickListener()** | This is called when the user selects a menu item. You will use onMenuItemClick() event handler to handle such event. |
| onCreateContextMenu() | **onCreateContextMenuItemListener()** | This is called when the context menu is being built(as the result of a sustained "long click) |

There are many more event listeners available as a part of View class like OnHoverListener, OnDragListener etc which may be needed for your application.

Original docs: [Touch & Input](https://developer.android.com/guide/input)

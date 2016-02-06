# Generic-List-Adapter
A generic `ListAdapter` for Android that aims to remove the need for most custom `ListAdapter`s.

# How it Works
The `GenericListAdapter` takes in the base `Layout` (by id) and a list of `Objects`. It parses all children of the `Layout` and then maps each `Object` to a view 

# How to Use 
1) Download the `GenericListAdapter` module and add it to your project.

2) Import `GenericListAdapter`.

3) Pass in the layout id of the base layout to the `GenericListAdapter`. Additionally, you can pass in objects via `varargs` or `ArrayList` in the order that they would appear in the layout file, in descending order. The `GenericListAdapter` takes care of the rest for you!

# Supported Views
A downside of this method is that I have to manually support each `View`. However, it's fairly straightforward to add support for new `View` types. Below is a list of currently supported `View`s and `Object`s.

# TextView
Supports: CharSequence, String, and all subclasses.
# ImageView
Supports: Drawable, Bitmap, URI, Matrix, and all subclasses.

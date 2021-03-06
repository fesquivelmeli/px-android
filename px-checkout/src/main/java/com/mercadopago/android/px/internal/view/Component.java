package com.mercadopago.android.px.internal.view;

import androidx.annotation.NonNull;

public class Component<T, S> {

    public T props;
    public S state;
    private ActionDispatcher dispatcher;
    private boolean viewAttached = false;

    public Component(@NonNull final T props) {
        this(props, null);
    }

    public Component(@NonNull final T props, @NonNull final ActionDispatcher dispatcher) {
        this.props = props;
        this.dispatcher = dispatcher;
        setProps(props);
    }

    protected void viewAttachedToWindow() {
        viewAttached = true;
        onViewAttachedToWindow();
    }

    public void onViewAttachedToWindow() {
        // Override method to implement custom logic
    }

    protected void viewDetachedFromWindow() {
        viewAttached = false;
        onViewDetachedFromWindow();
    }

    public void onViewDetachedFromWindow() {
        // Override method to implement custom logic
    }

    public boolean isViewAttached() {
        return viewAttached;
    }

    public ActionDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(@NonNull final ActionDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void setProps(@NonNull final T props) {
        this.props = props;
    }

    public void setState(@NonNull final S state) {
        if (isViewAttached()) {
            this.state = state;
            if (dispatcher != null) {
                dispatcher.dispatch(new PropsUpdatedAction());
            }
        }
    }
}

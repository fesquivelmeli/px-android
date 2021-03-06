package com.mercadopago.android.px.internal.view;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mercadopago.android.px.internal.util.TextUtil;

public abstract class Renderer<T extends Component> {

    private T component;
    private Context context;

    public void setComponent(@NonNull final T component) {
        this.component = component;
    }

    public void setContext(@NonNull final Context context) {
        this.context = context;
    }

    public View render() {
        return render(null);
    }

    public View render(@Nullable final ViewGroup parent) {
        final View view = render(component, context, parent);
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                component.viewAttachedToWindow();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                component.viewDetachedFromWindow();
            }
        });
        return view;
    }

    protected abstract View render(@NonNull final T component,
        @NonNull final Context context,
        @Nullable final ViewGroup parent);

    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent) {
        return LayoutInflater.from(context).inflate(layout, parent);
    }

    protected void setText(@NonNull final TextView view, @StringRes final int resource) {
        try {
            String text = context.getResources().getString(resource);
            if (text.isEmpty()) {
                view.setVisibility(View.GONE);
            } else {
                view.setText(text);
            }
        } catch (final Resources.NotFoundException ex) {
            //Todo: add to tracker
            view.setVisibility(View.GONE);
        }
    }

    protected void setText(@NonNull final TextView view, @Nullable final String text) {
        if (TextUtil.isEmpty(text)) {
            view.setVisibility(View.GONE);
        } else {
            view.setText(text);
        }
    }

    protected void setText(@NonNull final TextView view, @Nullable final CharSequence text) {
        if (TextUtil.isEmpty(text)) {
            view.setVisibility(View.GONE);
        } else {
            view.setText(text);
        }
    }

    protected void setText(@NonNull final TextView view, Spanned text) {
        if (text == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setText(text);
        }
    }
}

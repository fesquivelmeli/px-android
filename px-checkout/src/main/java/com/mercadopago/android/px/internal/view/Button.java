package com.mercadopago.android.px.internal.view;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import com.mercadolibre.android.ui.widgets.MeliButton;
import com.mercadopago.android.px.model.Action;

public abstract class Button extends CompactComponent<Button.Props, Button.Actions> {

    public interface Actions {
        void onClick(final Action action);
    }

    public static class Props {
        public final Action action;
        public final String label;

        public Props(final String label, final Action action) {
            this.action = action;
            this.label = label;
        }

        public Props(final String label) {
            action = new Action();
            this.label = label;
        }
    }

    public Button(final Props props, final Actions callBack) {
        super(props, callBack);
    }

    public abstract MeliButton getButtonView(@NonNull final Context context);

    @Override
    public View render(@NonNull final ViewGroup parent) {
        final MeliButton view = getButtonView(parent.getContext());
        view.setText(props.label);
        view.setOnClickListener(v -> getActions().onClick(props.action));
        return view;
    }
}
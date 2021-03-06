package com.mercadopago.android.px.model.exceptions;

import java.io.IOException;

public final class NoConnectivityException extends IOException {
    private static final String NO_CONNECTION_AVAILABLE = "No connection available";

    public NoConnectivityException() {
        super(NO_CONNECTION_AVAILABLE);
    }
}

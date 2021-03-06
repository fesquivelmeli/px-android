package com.mercadopago.android.px.testcheckout.pages;

import androidx.test.core.app.ApplicationProvider;
import com.mercadopago.android.px.testcheckout.assertions.CheckoutValidator;
import com.mercadopago.android.testlib.pages.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.mercadopago.android.testlib.matchers.UtilityMatchers.withBackgroundColor;

public class RejectedPage extends PageObject<CheckoutValidator> {

    public RejectedPage() {
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    public RejectedPage(CheckoutValidator validator) {
        super(validator);
    }

    @Override
    public RejectedPage validate(CheckoutValidator validator) {
        validator.validate(this);
        return this;
    }

    public boolean isError() {
        onView(withId(com.mercadopago.android.px.R.id.header))
            .check(matches(withBackgroundColor(
                ApplicationProvider.getApplicationContext().getResources()
                    .getColor(com.mercadopago.android.px.R.color.ui_components_error_color))));
        return true;
    }
}
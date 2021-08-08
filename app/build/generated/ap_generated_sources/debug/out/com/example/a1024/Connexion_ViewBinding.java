// Generated code from Butter Knife. Do not modify!
package com.example.a1024;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Connexion_ViewBinding implements Unbinder {
  private Connexion target;

  @UiThread
  public Connexion_ViewBinding(Connexion target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Connexion_ViewBinding(Connexion target, View source) {
    this.target = target;

    target.coordinatorLayout = Utils.findRequiredViewAsType(source, R.id.main_activity_coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Connexion target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.coordinatorLayout = null;
  }
}

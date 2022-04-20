package com.torgeirln.guitartuner.ui.base;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionsActivity extends BaseActivity {
    private final Map<Integer, PermissionsRequest> permissionsRequestMap = new HashMap<>();

    protected void checkPermissions(@NonNull PermissionsRequest permissionsRequest) {
        List<String> permissionsNotGranted = new ArrayList<>();
        for (String permission : permissionsRequest.permissions) {
            boolean permissionGranted = ActivityCompat.checkSelfPermission(this, permission)
                    == PackageManager.PERMISSION_GRANTED;
            if (permissionGranted) {
                permissionsRequest.permissionsListener.onRequestPermissionsResult(permission, true);
            } else {
                permissionsNotGranted.add(permission);
            }
        }

        if (!permissionsNotGranted.isEmpty()) {
            permissionsRequestMap.put(permissionsRequest.requestCode, permissionsRequest);
            String[] permissionsArray = permissionsNotGranted.toArray(new String[0]);
            requestPermissions(permissionsArray, permissionsRequest.requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionsRequest permissionsRequest = permissionsRequestMap.get(requestCode);
        if (permissionsRequest != null) {
            for (int i = 0; i < permissions.length; i++) {
                permissionsRequest.permissionsListener
                        .onRequestPermissionsResult(
                                permissions[i],
                                grantResults[i] == PackageManager.PERMISSION_GRANTED);
            }
        }
    }


    public interface PermissionsListener {
        void onRequestPermissionsResult(@NonNull String permission, boolean permissionGranted);
    }


    public static class PermissionsRequest {
        public final int requestCode;
        public final List<String> permissions;
        public final PermissionsListener permissionsListener;

        public PermissionsRequest(
                int requestCode,
                List<String> permissions,
                @NonNull PermissionsListener permissionsListener
        ) {
            this.requestCode = requestCode;
            this.permissions = permissions;
            this.permissionsListener = permissionsListener;
        }

        public PermissionsRequest(
                int requestCode,
                String permission,
                @NonNull PermissionsListener permissionsListener
        ) {
            this.requestCode = requestCode;
            this.permissions = Collections.singletonList(permission);
            this.permissionsListener = permissionsListener;
        }
    }

}
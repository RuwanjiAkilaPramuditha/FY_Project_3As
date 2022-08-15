package com.fyproject.fy_project_3as.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModelUser {

    public String student_id = "";

    public String user_name = "";
    public String email = "";
    public String school_name = "";
    public String phone_number = "";
    public String password = "";
    public String confirm_password = "";
    public String profile_photo = "";

    @Nullable
    public static List<ModelUser> listAll(@NotNull Class<ModelUser> java) {
        return null;
    }

    public static void save(@NotNull ModelUser modelUser) {

    }
}

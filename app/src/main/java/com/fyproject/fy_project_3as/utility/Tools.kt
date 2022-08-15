package com.fyproject.fy_project_3as.utility

import com.fyproject.fy_project_3as.model.ModelUser

class Tools {
    companion object{
        fun get_signed_in_user(): ModelUser? {
            return try {
                val allUser: List<ModelUser> = ModelUser.listAll(ModelUser::class.java)?:return null

                if (allUser.isEmpty()){
                    null
                }else allUser[0]
            }catch (e:Exception){
                null
            }
        }
    }


}
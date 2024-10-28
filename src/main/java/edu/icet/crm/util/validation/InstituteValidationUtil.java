package edu.icet.crm.util.validation;

import edu.icet.crm.entity.InstituteEntity;
import edu.icet.crm.model.Institute;

public class InstituteValidationUtil {
    private static InstituteValidationUtil instance;
    private InstituteValidationUtil(){}

    public static InstituteValidationUtil getInstance() {
        return instance==null?instance = new InstituteValidationUtil():instance;
    }
    public Boolean validateInstitute(InstituteEntity institute){
        return true;
    }
}


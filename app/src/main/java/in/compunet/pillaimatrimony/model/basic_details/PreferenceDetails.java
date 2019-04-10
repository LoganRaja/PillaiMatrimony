package in.compunet.pillaimatrimony.model.basic_details;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PreferenceDetails implements Serializable {
    @SerializedName("flag")
    String flag;
    @SerializedName("key")
    String key;
    @SerializedName("userId")
    String userId;

    @SerializedName("age")
    String age;
    @SerializedName("ageId")
    String ageId;
    @SerializedName("height")
    String height;
    @SerializedName("heightId")
    String heightId;

    @SerializedName("ageFrom")
    String ageFrom;
    @SerializedName("ageTo")
    String ageTo;
    @SerializedName("heightFrom")
    String heightFrom;
    @SerializedName("heightTo")
    String heightTo;

    @SerializedName("ageFromId")
    String ageFromId;
    @SerializedName("ageToId")
    String ageToId;
    @SerializedName("heightFromId")
    String heightFromId;
    @SerializedName("heightToId")
    String heightToId;


    @SerializedName("maritalStatus")
    String maritalStatus;
    @SerializedName("maritalStatusId")
    String maritalStatusId;

    @SerializedName("motherToungue")
    String motherToungue;
    @SerializedName("motherTongue")
    String motherTongue;

    @SerializedName("motherToungueId")
    String motherToungueId;
    @SerializedName("motherTongueId")
    String motherTongueId;

    @SerializedName("physicalStatus")
    String physicalStatus;
    @SerializedName("physicalStatusId")
    String physicalStatusId;
    @SerializedName("complexion")
    String complexion;
    @SerializedName("complexionId")
    String complexionId;
    @SerializedName("eatingHabbits")
    String eatingHabbits;
    @SerializedName("eatingHabbitsId")
    String eatingHabbitsId;
    @SerializedName("drinkingHabbits")
    String drinkingHabbits;
    @SerializedName("drinkingHabbitsId")
    String drinkingHabbitsId;
    @SerializedName("smokingHabbits")
    String smokingHabbits;
    @SerializedName("smokingHabbitsId")
    String smokingHabbitsId;

    @SerializedName("educationCategory")
    String educationCategory;
    @SerializedName("eduCollege")
    String eduCollege;
    @SerializedName("eduDetails")
    String eduDetails;
    @SerializedName("occupation")
    String occupation;
    @SerializedName("occuDetail")
    String occuDetail;
    @SerializedName("employedIn")
    String employedIn;
    @SerializedName("annualIncome")
    String annualIncome;
    @SerializedName("educationCategoryId")
    String educationCategoryId;
    @SerializedName("occupationId")
    String occupationId;
    @SerializedName("employedInId")
    String employedInId;
    @SerializedName("annualIncomeId")
    String annualIncomeId;


    @SerializedName("country")
    String country;
    @SerializedName("state")
    String state;
    @SerializedName("district")
    String district;
    @SerializedName("citizenship")
    String citizenship;
    @SerializedName("countryId")
    String countryId;
    @SerializedName("stateId")
    String stateId;
    @SerializedName("districtId")
    String districtId;
    @SerializedName("citizenshipId")
    String citizenshipId;

    @SerializedName("religion")
    String religion;
    @SerializedName("caste")
    String caste;
    @SerializedName("subCaste")
    String subCaste;
    @SerializedName("gothram")
    String gothram;
    @SerializedName("star")
    String star;
    @SerializedName("raasi")
    String raasi;
    @SerializedName("dhosham")
    String dhosham;
    @SerializedName("subCasteId")
    String subCasteId;
    @SerializedName("starId")
    String starId;
    @SerializedName("raasiId")
    String raasiId;
    @SerializedName("dhoshamId")
    String dhoshamId;

    @SerializedName("familyValues")
    String familyValues;
    @SerializedName("familyType")
    String familyType;
    @SerializedName("familyStatus")
    String familyStatus;
    @SerializedName("fatherOccupation")
    String fatherOccupation;
    @SerializedName("motherOccupation")
    String motherOccupation;
    @SerializedName("noOfBros")
    String noOfBros;
    @SerializedName("noOfSis")
    String noOfSis;
    @SerializedName("familyValuesId")
    String familyValuesId;
    @SerializedName("familyTypeId")
    String familyTypeId;
    @SerializedName("familyStatusId")
    String familyStatusId;
    @SerializedName("fatherOccupationId")
    String fatherOccupationId;
    @SerializedName("motherOccupationId")
    String motherOccupationId;
    @SerializedName("noOfBrosId")
    String noOfBrosId;
    @SerializedName("noOfSisId")
    String noOfSisId;

    @SerializedName("aboutMe")
    String aboutMe;
    @SerializedName("aboutFam")
    String aboutFamily;
    @SerializedName("hobbies")
    String hobbies;

    @SerializedName("interest")
    String interest;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public String getEatingHabbits() {
        return eatingHabbits;
    }

    public void setEatingHabbits(String eatingHabbits) {
        this.eatingHabbits = eatingHabbits;
    }

    public String getDrinkingHabbits() {
        return drinkingHabbits;
    }

    public void setDrinkingHabbits(String drinkingHabbits) {
        this.drinkingHabbits = drinkingHabbits;
    }

    public String getSmokingHabbits() {
        return smokingHabbits;
    }

    public void setSmokingHabbits(String smokingHabbits) {
        this.smokingHabbits = smokingHabbits;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getPhysicalStatusId() {
        return physicalStatusId;
    }

    public void setPhysicalStatusId(String physicalStatusId) {
        this.physicalStatusId = physicalStatusId;
    }

    public String getEatingHabbitsId() {
        return eatingHabbitsId;
    }

    public void setEatingHabbitsId(String eatingHabbitsId) {
        this.eatingHabbitsId = eatingHabbitsId;
    }

    public String getDrinkingHabbitsId() {
        return drinkingHabbitsId;
    }

    public void setDrinkingHabbitsId(String drinkingHabbitsId) {
        this.drinkingHabbitsId = drinkingHabbitsId;
    }

    public String getSmokingHabbitsId() {
        return smokingHabbitsId;
    }

    public void setSmokingHabbitsId(String smokingHabbitsId) {
        this.smokingHabbitsId = smokingHabbitsId;
    }

    public String getComplexionId() {
        return complexionId;
    }

    public void setComplexionId(String complexionId) {
        this.complexionId = complexionId;
    }

    public String getMotherToungueId() {
        return motherToungueId;
    }

    public void setMotherToungueId(String motherToungueId) {
        this.motherToungueId = motherToungueId;
    }
    public String getEducationCategory() {
        return educationCategory;
    }

    public void setEducationCategory(String educationCategory) {
        this.educationCategory = educationCategory;
    }

    public String getEduCollege() {
        return eduCollege;
    }

    public void setEduCollege(String eduCollege) {
        this.eduCollege = eduCollege;
    }

    public String getEduDetails() {
        return eduDetails;
    }

    public void setEduDetails(String eduDetails) {
        this.eduDetails = eduDetails;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccuDetail() {
        return occuDetail;
    }

    public void setOccuDetail(String occuDetail) {
        this.occuDetail = occuDetail;
    }

    public String getEmployedIn() {
        return employedIn;
    }

    public void setEmployedIn(String employedIn) {
        this.employedIn = employedIn;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getEducationCategoryId() {
        return educationCategoryId;
    }

    public void setEducationCategoryId(String educationCategoryId) {
        this.educationCategoryId = educationCategoryId;
    }

    public String getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(String occupationId) {
        this.occupationId = occupationId;
    }

    public String getEmployedInId() {
        return employedInId;
    }

    public void setEmployedInId(String employedInId) {
        this.employedInId = employedInId;
    }

    public String getAnnualIncomeId() {
        return annualIncomeId;
    }

    public void setAnnualIncomeId(String annualIncomeId) {
        this.annualIncomeId = annualIncomeId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(String citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public String getGothram() {
        return gothram;
    }

    public void setGothram(String gothram) {
        this.gothram = gothram;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getRaasi() {
        return raasi;
    }

    public void setRaasi(String raasi) {
        this.raasi = raasi;
    }

    public String getDhosham() {
        return dhosham;
    }

    public void setDhosham(String dhosham) {
        this.dhosham = dhosham;
    }

    public String getSubCasteId() {
        return subCasteId;
    }

    public void setSubCasteId(String subCasteId) {
        this.subCasteId = subCasteId;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public String getRaasiId() {
        return raasiId;
    }

    public void setRaasiId(String raasiId) {
        this.raasiId = raasiId;
    }

    public String getDhoshamId() {
        return dhoshamId;
    }

    public void setDhoshamId(String dhoshamId) {
        this.dhoshamId = dhoshamId;
    }

    public String getFamilyValues() {
        return familyValues;
    }

    public void setFamilyValues(String familyValues) {
        this.familyValues = familyValues;
    }

    public String getFamilyType() {
        return familyType;
    }

    public void setFamilyType(String familyType) {
        this.familyType = familyType;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getNoOfBros() {
        return noOfBros;
    }

    public void setNoOfBros(String noOfBros) {
        this.noOfBros = noOfBros;
    }

    public String getNoOfSis() {
        return noOfSis;
    }

    public void setNoOfSis(String noOfSis) {
        this.noOfSis = noOfSis;
    }

    public String getFamilyValuesId() {
        return familyValuesId;
    }

    public void setFamilyValuesId(String familyValuesId) {
        this.familyValuesId = familyValuesId;
    }

    public String getFamilyTypeId() {
        return familyTypeId;
    }

    public void setFamilyTypeId(String familyTypeId) {
        this.familyTypeId = familyTypeId;
    }

    public String getFamilyStatusId() {
        return familyStatusId;
    }

    public void setFamilyStatusId(String familyStatusId) {
        this.familyStatusId = familyStatusId;
    }

    public String getFatherOccupationId() {
        return fatherOccupationId;
    }

    public void setFatherOccupationId(String fatherOccupationId) {
        this.fatherOccupationId = fatherOccupationId;
    }

    public String getMotherOccupationId() {
        return motherOccupationId;
    }

    public void setMotherOccupationId(String motherOccupationId) {
        this.motherOccupationId = motherOccupationId;
    }

    public String getNoOfBrosId() {
        return noOfBrosId;
    }

    public void setNoOfBrosId(String noOfBrosId) {
        this.noOfBrosId = noOfBrosId;
    }

    public String getNoOfSisId() {
        return noOfSisId;
    }

    public void setNoOfSisId(String noOfSisId) {
        this.noOfSisId = noOfSisId;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAboutFamily() {
        return aboutFamily;
    }

    public void setAboutFamily(String aboutFamily) {
        this.aboutFamily = aboutFamily;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(String ageFrom) {
        this.ageFrom = ageFrom;
    }

    public String getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(String ageTo) {
        this.ageTo = ageTo;
    }

    public String getHeightFrom() {
        return heightFrom;
    }

    public void setHeightFrom(String heightFrom) {
        this.heightFrom = heightFrom;
    }

    public String getHeightTo() {
        return heightTo;
    }

    public void setHeightTo(String heightTo) {
        this.heightTo = heightTo;
    }

    public String getAgeFromId() {
        return ageFromId;
    }

    public void setAgeFromId(String ageFromId) {
        this.ageFromId = ageFromId;
    }

    public String getAgeToId() {
        return ageToId;
    }

    public void setAgeToId(String ageToId) {
        this.ageToId = ageToId;
    }

    public String getHeightFromId() {
        return heightFromId;
    }

    public void setHeightFromId(String heightFromId) {
        this.heightFromId = heightFromId;
    }

    public String getHeightToId() {
        return heightToId;
    }

    public void setHeightToId(String heightToId) {
        this.heightToId = heightToId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeightId() {
        return heightId;
    }

    public void setHeightId(String heightId) {
        this.heightId = heightId;
    }

    public String getMotherToungue() {
        return motherToungue;
    }

    public void setMotherToungue(String motherToungue) {
        this.motherToungue = motherToungue;
    }

    public String getMotherTongueId() {
        return motherTongueId;
    }

    public void setMotherTongueId(String motherTongueId) {
        this.motherTongueId = motherTongueId;
    }

    public String getAgeId() {
        return ageId;
    }

    public void setAgeId(String ageId) {
        this.ageId = ageId;
    }
}

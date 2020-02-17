package com.example.backpack.Repository;

import android.util.Log;

import java.util.ArrayList;

public class CityNamesRepository {

    private ArrayList<String> cityNamesIranEnglish;
    private ArrayList<String> cityNamesIranPersian;
    private ArrayList<String> cityNamesIranEnglishSolid;
    private ArrayList<String> cityNamesWorldEnglish;
    private ArrayList<String> cityNamesWorldPersian;
    private ArrayList<String> cityNamesWorldEnglishSolid;

    public CityNamesRepository() {
        initializeIranCityNames();
        initializeWorldCityNames();
    }

    private void initializeIranCityNames() {
        cityNamesIranEnglish = new ArrayList<>();
        cityNamesIranPersian = new ArrayList<>();
        cityNamesIranEnglishSolid = new ArrayList<>();

        cityNamesIranEnglish.add("Arak, IR");
        cityNamesIranPersian.add("اراک");
        cityNamesIranEnglish.add("Ardabil, IR");
        cityNamesIranPersian.add("اردبیل");
        cityNamesIranEnglish.add("Urmia, IR");
        cityNamesIranPersian.add("ارومیه");
        cityNamesIranEnglish.add("Isfahan, IR");
        cityNamesIranPersian.add("اصفهان");
        cityNamesIranEnglish.add("Ahvaz, IR");
        cityNamesIranPersian.add("اهواز");
        cityNamesIranEnglish.add("Ilam, IR");
        cityNamesIranPersian.add("ایلام");
        cityNamesIranEnglish.add("Bojnurd, IR");
        cityNamesIranPersian.add("بجنورد");
        cityNamesIranEnglish.add("Bandar Bushehr, IR");
        cityNamesIranPersian.add("بندر بوشهر");
        cityNamesIranEnglish.add("Bandar Abbas, IR");
        cityNamesIranPersian.add("بندرعباس");
        cityNamesIranEnglish.add("Birjand, IR");
        cityNamesIranPersian.add("بیرجند");
        cityNamesIranEnglish.add("Tabriz, IR");
        cityNamesIranPersian.add("تبریز");
        cityNamesIranEnglish.add("Tehran, IR");
        cityNamesIranPersian.add("تهران");
        cityNamesIranEnglish.add("Khoram Abad, IR");
        cityNamesIranPersian.add("خرم آباد");
        cityNamesIranEnglish.add("Rasht, IR");
        cityNamesIranPersian.add("رشت");
        cityNamesIranEnglish.add("Zahedan, IR");
        cityNamesIranPersian.add("زاهدان");
        cityNamesIranEnglish.add("Zanjan, IR");
        cityNamesIranPersian.add("زنجان");
        cityNamesIranEnglish.add("Sari, IR");
        cityNamesIranPersian.add("ساری ");
        cityNamesIranEnglish.add("Semnan, IR");
        cityNamesIranPersian.add("سمنان");
        cityNamesIranEnglish.add("Sanandaj, IR");
        cityNamesIranPersian.add("سنندج");
        cityNamesIranEnglish.add("Shiraz, IR");
        cityNamesIranPersian.add("شیراز");
        cityNamesIranEnglish.add("Qazvin, IR");
        cityNamesIranPersian.add("قزوین");
        cityNamesIranEnglish.add("Qom, IR");
        cityNamesIranPersian.add("قم");
        cityNamesIranEnglish.add("Karaj, IR");
        cityNamesIranPersian.add("کرج");
        cityNamesIranEnglish.add("Kerman, IR");
        cityNamesIranPersian.add("کرمان");
        cityNamesIranEnglish.add("Kermanshah, IR");
        cityNamesIranPersian.add("کرمانشاه");
        cityNamesIranEnglish.add("Gorgan, IR");
        cityNamesIranPersian.add("گرگان");
        cityNamesIranEnglish.add("Mashhad, IR");
        cityNamesIranPersian.add("مشهد");
        cityNamesIranEnglish.add("Hamedan, IR");
        cityNamesIranPersian.add("همدان ");
        cityNamesIranEnglish.add("Yasuj, IR");
        cityNamesIranPersian.add("یاسوج");
        cityNamesIranEnglish.add("Yazd, IR");
        cityNamesIranPersian.add("یزد");

        for (int i = 0; i < cityNamesIranPersian.size(); i++){
            String[] split = cityNamesIranEnglish.get(i).split(",");
            cityNamesIranEnglishSolid.add(split[0]) ;

        }

    }

    private void initializeWorldCityNames() {
        cityNamesWorldEnglish = new ArrayList<>();
        cityNamesWorldPersian = new ArrayList<>();
        cityNamesWorldEnglishSolid = new ArrayList<>();

        cityNamesWorldEnglish.add("Athens, GR");
        cityNamesWorldPersian.add("آتن");
        cityNamesWorldEnglish.add("Astana, KZ");
        cityNamesWorldPersian.add("آستانه");
        cityNamesWorldEnglish.add("Amsterdam, NL");
        cityNamesWorldPersian.add("آمستردام");
        cityNamesWorldEnglish.add("Ankara, TR");
        cityNamesWorldPersian.add("آنکارا");
        cityNamesWorldEnglish.add("Abu Dhabi, AE");
        cityNamesWorldPersian.add("ابوظبی");
        cityNamesWorldEnglish.add("Ottawa, CA");
        cityNamesWorldPersian.add("اتاوا");
        cityNamesWorldEnglish.add("Stockholm, SE");
        cityNamesWorldPersian.add("استکهلم");
        cityNamesWorldEnglish.add("Islamabad, PK");
        cityNamesWorldPersian.add("اسلام آباد");
        cityNamesWorldEnglish.add("Oslo, NO");
        cityNamesWorldPersian.add("اسلو");
        cityNamesWorldEnglish.add("Yerevan, AM");
        cityNamesWorldPersian.add("ایروان");
        cityNamesWorldEnglish.add("Baku, AZ");
        cityNamesWorldPersian.add("باکو");
        cityNamesWorldEnglish.add("Bangkok, TH");
        cityNamesWorldPersian.add("بانکوک");
        cityNamesWorldEnglish.add("Bucharest, RO");
        cityNamesWorldPersian.add("بخارست");
        cityNamesWorldEnglish.add("Brasilia, BR");
        cityNamesWorldPersian.add("برازیلیا");
        cityNamesWorldEnglish.add("Berlin, DE");
        cityNamesWorldPersian.add("برلین");
        cityNamesWorldEnglish.add("Brussels, BE");
        cityNamesWorldPersian.add("بروکسل");
        cityNamesWorldEnglish.add("Baghdad, IQ");
        cityNamesWorldPersian.add("بغداد");
        cityNamesWorldEnglish.add("Buenos Aires, AR");
        cityNamesWorldPersian.add("بوئنوس آیرس");
        cityNamesWorldEnglish.add("Budapest, HU");
        cityNamesWorldPersian.add("بوداپست");
        cityNamesWorldEnglish.add("Beirut, LB");
        cityNamesWorldPersian.add("بیروت");
        cityNamesWorldEnglish.add("Paris, FR");
        cityNamesWorldPersian.add("پاریس");
        cityNamesWorldEnglish.add("Prague, CZ");
        cityNamesWorldPersian.add("پراگ");
        cityNamesWorldEnglish.add("Beijing, CN");
        cityNamesWorldPersian.add("پکن");
        cityNamesWorldEnglish.add("Tashkent, UZ");
        cityNamesWorldPersian.add("تاشکند");
        cityNamesWorldEnglish.add("Taipei, TW");
        cityNamesWorldPersian.add("تایپه");
        cityNamesWorldEnglish.add("Tbilisi, GE");
        cityNamesWorldPersian.add("تفلیس");
        cityNamesWorldEnglish.add("Tokyo, JP");
        cityNamesWorldPersian.add("توکیو");
        cityNamesWorldEnglish.add("Jakarta, ID");
        cityNamesWorldPersian.add("جاکارتا");
        cityNamesWorldEnglish.add("Damascus, SY");
        cityNamesWorldPersian.add("دمشق");
        cityNamesWorldEnglish.add("Doha, QA");
        cityNamesWorldPersian.add("دوحه");
        cityNamesWorldEnglish.add("Dushanbe, TJ");
        cityNamesWorldPersian.add("دوشنبه");
        cityNamesWorldEnglish.add("New Delhi, IN");
        cityNamesWorldPersian.add("دهلی نو");
        cityNamesWorldEnglish.add("Rome, IT");
        cityNamesWorldPersian.add("رم");
        cityNamesWorldEnglish.add("Riyadh, SA");
        cityNamesWorldPersian.add("ریاض");
        cityNamesWorldEnglish.add("Seoul, KR");
        cityNamesWorldPersian.add("سئول");
        cityNamesWorldEnglish.add("Sanaa, YE");
        cityNamesWorldPersian.add("صنعا");
        cityNamesWorldEnglish.add("Ashgabat, TM");
        cityNamesWorldPersian.add("عشق آباد");
        cityNamesWorldEnglish.add("Cairo, EG");
        cityNamesWorldPersian.add("قاهره");
        cityNamesWorldEnglish.add("Kabul, AF");
        cityNamesWorldPersian.add("کابل");
        cityNamesWorldEnglish.add("Sydney, AU");
        cityNamesWorldPersian.add("سیدنی");
        cityNamesWorldEnglish.add("Copenhagen, DK");
        cityNamesWorldPersian.add("کپنهاگ");
        cityNamesWorldEnglish.add("Kuala Lumpur, MY");
        cityNamesWorldPersian.add("کوالالامپور");
        cityNamesWorldEnglish.add("London, GB");
        cityNamesWorldPersian.add("لندن");
        cityNamesWorldEnglish.add("Lisbon, PT");
        cityNamesWorldPersian.add("لیسبون");
        cityNamesWorldEnglish.add("Madrid, ES");
        cityNamesWorldPersian.add("مادرید");
        cityNamesWorldEnglish.add("Washington DC., US");
        cityNamesWorldPersian.add("واشینگتن");
        cityNamesWorldEnglish.add("Warsaw, PL");
        cityNamesWorldPersian.add("ورشو");
        cityNamesWorldEnglish.add("Vienna, AT");
        cityNamesWorldPersian.add("وین");

        for (int i = 0; i < cityNamesWorldPersian.size(); i++){
            String[] split = cityNamesWorldEnglish.get(i).split(",");
            cityNamesWorldEnglishSolid.add(split[0]) ;
        }
    }

    public ArrayList<String> getCityNamesIranEnglish() {
        return cityNamesIranEnglish;
    }

    public void setCityNamesIranEnglish(ArrayList<String> cityNamesIranEnglish) {
        this.cityNamesIranEnglish = cityNamesIranEnglish;
    }

    public ArrayList<String> getCityNamesIranPersian() {
        return cityNamesIranPersian;
    }

    public void setCityNamesIranPersian(ArrayList<String> cityNamesIranPersian) {
        this.cityNamesIranPersian = cityNamesIranPersian;
    }

    public ArrayList<String> getCityNamesIranEnglishSolid() {
        return cityNamesIranEnglishSolid;
    }

    public void setCityNamesIranEnglishSolid(ArrayList<String> cityNamesIranEnglishSolid) {
        this.cityNamesIranEnglishSolid = cityNamesIranEnglishSolid;
    }

    public ArrayList<String> getCityNamesWorldEnglish() {
        return cityNamesWorldEnglish;
    }

    public void setCityNamesWorldEnglish(ArrayList<String> cityNamesWorldEnglish) {
        this.cityNamesWorldEnglish = cityNamesWorldEnglish;
    }

    public ArrayList<String> getCityNamesWorldPersian() {
        return cityNamesWorldPersian;
    }

    public void setCityNamesWorldPersian(ArrayList<String> cityNamesWorldPersian) {
        this.cityNamesWorldPersian = cityNamesWorldPersian;
    }

    public ArrayList<String> getCityNamesWorldEnglishSolid() {
        return cityNamesWorldEnglishSolid;
    }

    public void setCityNamesWorldEnglishSolid(ArrayList<String> cityNamesWorldEnglishSolid) {
        this.cityNamesWorldEnglishSolid = cityNamesWorldEnglishSolid;
    }
}

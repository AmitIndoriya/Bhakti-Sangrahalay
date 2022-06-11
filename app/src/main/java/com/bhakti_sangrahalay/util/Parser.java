package com.bhakti_sangrahalay.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.model.AartiBean3;
import com.bhakti_sangrahalay.model.KathaBean;
import com.bhakti_sangrahalay.model.MantraBean;
import com.bhakti_sangrahalay.model.SunderKaandBean;
import com.bhakti_sangrahalay.model.SunderKandHomeBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {


   /* public ArrayList<AartiBean> aartiParser(String response) {
        ArrayList<AartiBean> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            AartiBean aartiBean;
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                aartiBean = new AartiBean();
                aartiBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                aartiBean.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                aartiBean.setUrl(jsonArray.getJSONObject(i).getString("url"));
                aartiBean.setId(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
                aartiBean.setAudioFileName(jsonArray.getJSONObject(i).getString("audiofilename"));
                arrayList.add(aartiBean);
            }

        } catch (Exception e) {
            Log.e("e", e.getMessage());
        }
        return arrayList;
    }

    public ArrayList<ChalishaBean> chalishaParser(String response) {
        ArrayList<ChalishaBean> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            ChalishaBean chalishaBean;
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                chalishaBean = new ChalishaBean();
                chalishaBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                chalishaBean.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                arrayList.add(chalishaBean);
            }

        } catch (Exception e) {

        }
        return arrayList;
    }*/

    public SunderKaandBean sunderKandParser(Context context, Resources resources, String response) {
        SunderKaandBean sunderKaandBean = new SunderKaandBean();
        try {
            JSONObject mainJsonObject = new JSONObject(response);

            ArrayList<SunderKaandBean.SunderKandArray> sunderKandArrayArrayList = new ArrayList<>();
            String heading = mainJsonObject.getString("heading");

            JSONArray SunderKandArray = mainJsonObject.getJSONArray("SunderKandArray");

            for (int i = 0; i < SunderKandArray.length(); i++) {

                JSONObject outerJsonObject = SunderKandArray.getJSONObject(i);

                SunderKaandBean.SunderKandArray sunderKandArray = sunderKaandBean.new SunderKandArray();
                String title = outerJsonObject.getString("title");
                String image = outerJsonObject.getString("image");
                final int resourceId = resources.getIdentifier(image, "drawable", context.getPackageName());

                Drawable drawable = resources.getDrawable(resourceId);
                ArrayList<SunderKaandBean.SunderKandArray.Choupai> choupaiArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Doha> dohaArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Chhand> chhandArrayList = new ArrayList<>();
                ArrayList<SunderKaandBean.SunderKandArray.Shortha> shorthaArrayList = new ArrayList<>();
                if (outerJsonObject.has("Choupai")) {
                    JSONArray chopaiJsonArray = outerJsonObject.getJSONArray("Choupai");
                    for (int j = 0; j < chopaiJsonArray.length(); j++) {
                        SunderKaandBean.SunderKandArray.Choupai choupai = sunderKandArray.new Choupai();
                        JSONObject innerJsonObject = chopaiJsonArray.getJSONObject(j);
                        String chopai = innerJsonObject.getString("chopai");
                        String meaning = innerJsonObject.getString("meaning");
                        choupai.setChopai(chopai);
                        choupai.setMeaning(meaning);
                        choupaiArrayList.add(choupai);
                    }
                }
                if (outerJsonObject.has("Doha")) {
                    JSONArray dohaJsonArray = outerJsonObject.getJSONArray("Doha");
                    for (int k = 0; k < dohaJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Doha doha = sunderKandArray.new Doha();
                        JSONObject innerJsonObject = dohaJsonArray.getJSONObject(k);
                        String dohaStr = innerJsonObject.getString("doha");
                        String meaning = innerJsonObject.getString("meaning");
                        doha.setDoha(dohaStr);
                        doha.setMeaning(meaning);
                        dohaArrayList.add(doha);
                    }
                }
                if (outerJsonObject.has("Chhand")) {
                    JSONArray chhandJsonArray = outerJsonObject.getJSONArray("Chhand");
                    for (int k = 0; k < chhandJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Chhand chhand = sunderKandArray.new Chhand();
                        JSONObject innerJsonObject = chhandJsonArray.getJSONObject(k);
                        String chhandStr = innerJsonObject.getString("chhand");
                        String meaning = innerJsonObject.getString("meaning");
                        chhand.setChhand(chhandStr);
                        chhand.setMeaning(meaning);
                        chhandArrayList.add(chhand);
                    }
                }
                if (outerJsonObject.has("Sortha")) {
                    JSONArray shorthaJsonArray = outerJsonObject.getJSONArray("Sortha");
                    for (int k = 0; k < shorthaJsonArray.length(); k++) {
                        SunderKaandBean.SunderKandArray.Shortha shortha = sunderKandArray.new Shortha();
                        JSONObject innerJsonObject = shorthaJsonArray.getJSONObject(k);
                        String sorthaStr = innerJsonObject.getString("sortha");
                        String meaning = innerJsonObject.getString("meaning");
                        shortha.setShortha(sorthaStr);
                        shortha.setMeaning(meaning);
                        shorthaArrayList.add(shortha);
                    }
                }


                sunderKandArray.setTitle(title);
                sunderKandArray.setDrawable(drawable);
                sunderKandArray.setChoupaiArrayList(choupaiArrayList);
                sunderKandArray.setDohaArrayList(dohaArrayList);
                sunderKandArray.setChhandArrayList(chhandArrayList);
                sunderKandArray.setShorthaArrayList(shorthaArrayList);
                sunderKandArrayArrayList.add(sunderKandArray);
            }

            sunderKaandBean.setHeading(heading);
            sunderKaandBean.setSunderKandArrayArrayList(sunderKandArrayArrayList);
        } catch (Exception e) {
            Log.i("Exception", e.getMessage());
        }
        return sunderKaandBean;
    }

   /* public ArrayList<AartiBean2> aartiListParser(String response) {
        ArrayList<AartiBean2> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            AartiBean2 aartiBean;
            SongDetailBean songDetailBean;
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                aartiBean = new AartiBean2();
                songDetailBean = new SongDetailBean();

                songDetailBean.setId(jsonArray.getJSONObject(i).getInt("id"));
                songDetailBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                //songDetailBean.setImage(jsonArray.getJSONObject(i).getString("image"));
                songDetailBean.setUrl(jsonArray.getJSONObject(i).getString("url"));
                songDetailBean.setSinger(jsonArray.getJSONObject(i).getString("singer"));
                songDetailBean.setDuration(jsonArray.getJSONObject(i).getString("duration"));
                songDetailBean.setAudiofilesize(jsonArray.getJSONObject(i).getString("audiofilesize"));
                songDetailBean.setAudiofilename(jsonArray.getJSONObject(i).getString("audiofilename"));
                songDetailBean.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                songDetailBean.setLocalUrl(GlobalVariables.storagePath + jsonArray.getJSONObject(i).getString("audiofilename"));
                aartiBean.setSongDetail(songDetailBean);
                arrayList.add(aartiBean);
            }

        } catch (Exception e) {
            Log.e("e", e.getMessage());
        }
        return arrayList;
    }*/
    public ArrayList<AartiBean3> aartiListParserNew(String response) {
        ArrayList<AartiBean3> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            AartiBean3 aartiBean;
            JSONArray jsonArray = jsonObject.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                aartiBean = new AartiBean3();
                aartiBean.setId(jsonArray.getJSONObject(i).getInt("id"));
                aartiBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                aartiBean.setUrl(jsonArray.getJSONObject(i).getString("url"));
                aartiBean.setSinger(jsonArray.getJSONObject(i).getString("singer"));
                aartiBean.setDuration(jsonArray.getJSONObject(i).getString("duration"));
                aartiBean.setAudiofilesize(jsonArray.getJSONObject(i).getString("audiofilesize"));
                aartiBean.setAudiofilename(jsonArray.getJSONObject(i).getString("audiofilename"));
                aartiBean.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                aartiBean.setLocalSaveUrl(GlobalVariables.storagePath + jsonArray.getJSONObject(i).getString("audiofilename"));
                aartiBean.setDownLoading(false);
                aartiBean.setProgressStatus(0);
                if(Utility.isFileExist(aartiBean.getAudiofilename(), Long.parseLong(aartiBean.getAudiofilesize()))){
                    aartiBean.setDownLoaded(true);
                }else{
                    aartiBean.setDownLoaded(false);
                }
                arrayList.add(aartiBean);
            }

        } catch (Exception e) {
            Log.e("e", e.getMessage());
        }
        return arrayList;
    }

    public KathaBean kathaParser(String response) {
        KathaBean kathaBean = new KathaBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            kathaBean.setTitle(jsonObject.getString("title"));
            kathaBean.setDesc(jsonObject.getString("desc"));
        } catch (Exception e) {

        }
        return kathaBean;
    }

    public ArrayList<KathaBean> kathaListParser(String response) {
        ArrayList<KathaBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            KathaBean kathaBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                kathaBean = new KathaBean();
                jsonObject = jsonArray.getJSONObject(i);
                kathaBean.setTitle(jsonObject.getString("title"));
                kathaBean.setDesc(jsonObject.getString("desc"));
                arrayList.add(kathaBean);
            }

        } catch (Exception e) {

        }
        return arrayList;
    }

    public ArrayList<MantraBean> mantraListParser(String response) {
        ArrayList<MantraBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            MantraBean mantraBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                mantraBean = new MantraBean();
                jsonObject = jsonArray.getJSONObject(i);
                mantraBean.setTitle(jsonObject.getString("title"));
                mantraBean.setContent(jsonObject.getString("desc"));
                arrayList.add(mantraBean);
            }

        } catch (Exception e) {

        }
        return arrayList;
    }

    public ArrayList<SunderKandHomeBean> sunderKandSlok(String response) {
        ArrayList<SunderKandHomeBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            SunderKandHomeBean sunderKandHomeBean;
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                sunderKandHomeBean = new SunderKandHomeBean();
                jsonObject = jsonArray.getJSONObject(i);
                sunderKandHomeBean.setDoha(jsonObject.getString("doha"));
                sunderKandHomeBean.setMeaning(jsonObject.getString("meaning"));
                arrayList.add(sunderKandHomeBean);
            }

        } catch (Exception e) {

        }
        return arrayList;
    }

}

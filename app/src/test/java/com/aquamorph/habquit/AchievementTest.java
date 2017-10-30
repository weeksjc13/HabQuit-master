package com.aquamorph.habquit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.aquamorph.habquit.activities.AchievementsActivity;
import com.aquamorph.habquit.adapter.AchievementAdapter;
import com.aquamorph.habquit.model.AchieveList;
import com.aquamorph.habquit.model.Achievement;
import com.google.common.base.Verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Shawn on 2/27/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config  (constants = BuildConfig.class, manifest = "../../../../../src/main/AndroidManifest.xml", sdk = 21 )

public class AchievementTest {
    private AchievementsActivity achievementsActivity= Robolectric.buildActivity(AchievementsActivity.class).create().get();
    private RecyclerView achievements = (RecyclerView) achievementsActivity.findViewById(R.id.achievements);

    @Test
    public void getAchieveList() throws Exception {
       /* AchieveList achieveList = new AchieveList();
        achieveList.setAchievementId(7);
        AchieveList testAchieveList = achievements.getAdapter(AchievementAdapter test1 */

        achievementsActivity.findViewById(R.id.achievements);
        Verify.verifyNotNull(achievements);
        


    }
}

package com.sleepygamers.game;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

import static com.sleepygamers.game.DodgeIt.ggg;

public class AndroidLauncher extends AndroidApplication implements PlayServices {
    private final static int requestCode = 1;
    private GameHelper gameHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.enableDebugLog(false);

        GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
            @Override
            public void onSignInFailed() {
            }

            @Override
            public void onSignInSucceeded() {
            }
        };

        gameHelper.setup(gameHelperListener);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new DodgeIt(this), config);
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {
        String str = "Your PlayStore Link";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
    }

    @Override
    public void unlockAchievement() {
        if (ggg == 1) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_50_classic));
        } else if (ggg == 2) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_100_classic));
        } else if (ggg == 3) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_gold_300_classic));
        } else if (ggg == 4) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_super_500_classic));
        } else if (ggg == 5) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_heroic_classic));
        } else if (ggg == 6) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_50_timeattack));
        } else if (ggg == 7) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_100_timeattack));
        } else if (ggg == 8) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_gold_150_timeattack));
        } else if (ggg == 9) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_super_200_timeattack));
        } else if (ggg == 10) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_heroic_timeattack));
        } else if (ggg == 11) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_50_hardcore));
        } else if (ggg == 12) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_first_100_hardcore));
        } else if (ggg == 13) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_gold_500_hardcore));
        } else if (ggg == 14) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_super_800_hardcore));
        } else if (ggg == 15) {
            Games.Achievements.unlock(gameHelper.getApiClient(),
                    getString(R.string.achievement_heroic_hardcore));
        }
    }

    @Override
    public void submitScore(int highScore) {
        if (isSignedIn() == true) {
            if (ggg == 21) {
                Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_classic), highScore);
            }
            if (ggg == 22) {
                Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_time_attack), highScore);
            }
            if (ggg == 23) {
                Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_hard_core), highScore);
            }
        }
    }

    @Override
    public void showAchievement() {
        if (isSignedIn() == true) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), requestCode);
        } else {
            signIn();
        }
    }


    @Override
    public void showScore() {
        if (isSignedIn() == true) {
            if (ggg == 21) {
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_classic)), requestCode);
            }
            if (ggg == 22) {
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_time_attack)), requestCode);
            }
            if (ggg == 23) {
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                        getString(R.string.leaderboard_hard_core)), requestCode);
            }
        } else {
            signIn();
        }
    }

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }
}

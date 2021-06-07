package es.imovildani.tanteofinal;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public TeamPoints TeamLoc  = new TeamPoints();
    public TeamPoints TeamVis = new TeamPoints();

    public MyViewModel() {
        this.TeamLoc = new  TeamPoints();
        this.TeamVis = new TeamPoints();

    }

    public TeamPoints getLocal() {
        return TeamLoc;
    }

    public TeamPoints getVisitante() {
        return TeamVis;
    }

    public void setLocal(TeamPoints local) {
        this.TeamLoc = local;
    }

    public void setVisitante(TeamPoints visitante) {
        this.TeamVis = TeamVis;
    }
}

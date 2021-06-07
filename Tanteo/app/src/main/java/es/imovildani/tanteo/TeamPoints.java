package es.imovildani.tanteo;

import androidx.lifecycle.ViewModel;

public class TeamPoints extends ViewModel {
    public TeamPoints local = new TeamPoints();
    public TeamPoints visitante= new TeamPoints();


    public TeamPoints getLocal() {
        return local;
    }

    public TeamPoints getVisitante() {
        return visitante;
    }
}

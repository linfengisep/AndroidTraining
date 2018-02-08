package isep.layouttraining;

/**
 * Created by linfengwang on 08/02/2018.
 */

public class Album {
    public String name;
    public int numSong;
    public int thumbnail;

    public Album(){
    }
    public Album(String name,int numSong,int thumbnail){
        this.name=name;
        this.numSong=numSong;
        this.thumbnail=thumbnail;
    }
}

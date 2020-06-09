package androis.myapp.pokemon;

public class pokemonmodel {

    private  String name;
    private  String url;

    public pokemonmodel(String n , String u ){

        this.name = n;
        this.url = u;

    }

    public String getName(){
        return this.name;
    }

    public String getUrl(){
        return this.url;
    }

}

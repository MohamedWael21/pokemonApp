package androis.myapp.pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder> {
    public ArrayList pokemons ;
    public Context context;
    public Adapter(ArrayList list, Context contxt){
       this.pokemons = list ;
       this.context = contxt ;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemonrow,parent,false);
        return new  myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
      final  pokemonmodel poke = (pokemonmodel)pokemons.get(position);
       holder.text.setText(poke.getName());
       holder.containerview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(context,pokemoninfo.class);
               //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

               intent.putExtra("pokemon_name",poke.getName());
               intent.putExtra("pkemon_url",poke.getUrl());

               context.startActivity(intent);


           }
       });

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        public TextView text;
        public ViewGroup containerview;

        public myviewholder(View view){
            super(view);
            this.text = view.findViewById(R.id.pokemonrow);
            this.containerview = view .findViewById(R.id.container);
        }

    }
}

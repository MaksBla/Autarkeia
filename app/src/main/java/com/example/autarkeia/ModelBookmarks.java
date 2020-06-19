package com.example.autarkeia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ModelBookmarks extends RecyclerView.Adapter<ModelBookmarks.MyViewHolder>  {

    List<String> tex = new ArrayList<String>();
    List<String> tagt = new ArrayList<String>();

    Context context;

  //  private OnItemClickListener<bookmarks> onItemClickListener;

    public ModelBookmarks(Context ct, List<String> texxt, List<String> tagtt){
        context = ct;
        tex = texxt;
        tagt=tagtt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bookmarks, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText.setText(tex.get(position));
        holder.myText.setTag(tagt.get(position));
    }

    @Override
    public int getItemCount() {
        //сколько элементов всего
        return tex.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText = itemView.findViewById(R.id.myTextView);

            myText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionIndex = getAdapterPosition();

                    switch (v.getTag().toString()) {
                        case "donotregretaboutanything":
                            Intent intent = new Intent(context,MainText.class);
                            intent.putExtra("nameTable", "donotregretaboutanything");
                            intent.putExtra("nameButton", "Ни о чем не жалей");
                             context.startActivity(intent);
                            break;
                        case "followthedreams":
                            Intent followthedreams =  new Intent(context,MainText.class);
                            followthedreams.putExtra("nameTable", "followthedreams");
                            followthedreams.putExtra("nameButton", "Следуй за мечтой");
                            context.startActivity(followthedreams);
                            break;
                        case "createyourfuture":
                            Intent glav3 =  new Intent(context,MainText.class);
                            glav3.putExtra("nameTable", "createyourfuture");
                            glav3.putExtra("nameButton", "Создавай свое будущее");
                            context.startActivity(glav3);
                            break;
                        case "emotionalintellect":
                            Intent glav4 =  new Intent(context,MainText.class);
                            glav4.putExtra("nameTable", "emotionalintellect");
                            glav4.putExtra("nameButton", "Эмоциональный интеллект");
                            context.startActivity(glav4);
                            break;
                        case "dotheopposite":
                            Intent glav5 = new Intent(context, MainText.class);
                            glav5.putExtra("nameTable", "dotheopposite");
                            glav5.putExtra("nameButton", "Поступай наоборот");
                            context.startActivity(glav5);
                            break;
                        case "gettoknowyourself":
                            Intent glav6 = new Intent(context, MainText.class);
                            glav6.putExtra("nameTable", "gettoknowyourself");
                            glav6.putExtra("nameButton", "Узнавай себя");
                            context.startActivity(glav6);
                            break;
                        case "donbeafraidtogetolder":
                            Intent glav7 = new Intent(context, MainText.class);
                            glav7.putExtra("nameTable", "donbeafraidtogetolder");
                            glav7.putExtra("nameButton", "Не бойся становиться старше");
                            context.startActivity(glav7);
                            break;
                        case "getoutofyourcomfortzone":
                            Intent glav8 = new Intent(context, MainText.class);
                            glav8.putExtra("nameTable", "getoutofyourcomfortzone");
                            glav8.putExtra("nameButton", "Выйди из зоны комфорта");
                            context.startActivity(glav8);
                            break;
                        case "appreciateyourlovedones":
                            Intent glav9 = new Intent(context, MainText.class);
                            glav9.putExtra("nameTable", "appreciateyourlovedones");
                            glav9.putExtra("nameButton", "Цени своих близких");
                            context.startActivity(glav9);
                            break;
                        case "yourselfasyouare":
                            Intent glav10 = new Intent(context, MainText.class);
                            glav10.putExtra("nameTable", "yourselfasyouare");
                            glav10.putExtra("nameButton", "Принимайте себя таким, какой вы есть");
                            context.startActivity(glav10);
                            break;
                        case "getenoughsleep":
                            Intent glav11 = new Intent(context, MainText.class);
                            glav11.putExtra("nameTable", "getenoughsleep");
                            glav11.putExtra("nameButton", "Высыпайся");
                            context.startActivity(glav11);
                            break;
                        case "begenerous":
                            Intent glav12 = new Intent(context, MainText.class);
                            glav12.putExtra("nameTable", "begenerous");
                            glav12.putExtra("nameButton", "Будьте великодушным");
                            context.startActivity(glav12);
                            break;
                        case "eatright":
                            Intent glav13 = new Intent(context, MainText.class);
                            glav13.putExtra("nameTable", "eatright");
                            glav13.putExtra("nameButton", "Питайтесь правильно");
                            context.startActivity(glav13);
                            break;
                    }
                }
            });
        }
    }
}
package com.example.psyhologyrf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psyhologyrf.ui.home.HomeFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryVew> {
    Context context;   // контекс что должно бытьна странице
    List<ModelButtom> category;  // список элементов
    private EditText answerText;
   // private TextView sayText;


    public CategoryAdapter(Context context, List<ModelButtom> category) {
        // Назначаем переданные данные локальным переменным
        this.context = context; // context
        this.category = category; // Данные класса сущности ArrayList
        // Данные класса сущности ArrayList


    }


    @NonNull
    @Override
    public categoryVew onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         /*  Класс LayoutInflater используется для создания экземпляров содержимого XML-файлов layout в соответствующие им объекты представления.
    Другими словами, он принимает XML-файл в качестве входных данных и создает из него объекты представления.*/

        // create a new view
        View categoryItems  = LayoutInflater.from(parent.getContext()).inflate(R.layout.buttomactiv, null);// дизайн можно скопировать
        // create ViewHolder
        return new categoryVew(categoryItems);//с какими элементами работаем



    }


    @Override
    public void onBindViewHolder(@NonNull categoryVew holder, @SuppressLint("RecyclerView") int position) {


        HomeFragment homeFragment = new HomeFragment();

        holder.CattextView.setText(category.get(position).getTitle());


        holder.card_pertanyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               // mListener.onFragment1DataListener(category.get(position).getTitle());


                homeFragment.showCategoryByActivity(category.get(position).getTitle());
                //  new HomeFragment().showCategoryByActivity(category.get(position).getId());

               // HomeFragment.MyCallbacktwo.onCallbacktwo(category.get(position).getTitle());
                //answerText.setText(category.get(position).getTitle());
                //------------------------------------------------------------------------------
               // homeFragment.getParentFragment().getView().findViewById(R.id.answerText);

                //new HomeFragment().getActivity().getSupportFragmentManager().findFragmentById(R.id.answerText);
               // new HomeFragment().getParentFragment().getView().findViewById(R.id.answerText);
               // holder.answerText.setText(category.get(position).getTitle());


             //   holder.answerText.setText(category.get(position).getTitle());

                System.out.println(category.get(position).getTitle() + " - " + category.get(position).getId());



            }
        });

      /*  viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.answerText.setText(simpleBot.sayInReturn(category.get(position).getTitle(), true));

               // homeFragment.showCategoryByActivity(holder.answerText.setText(category.get(position).getTitle()));
                 //homeFragment.showCategoryByActivity(category.get(position).getTitle());
              //  holder.sayText.setText(simpleBot.sayInReturn(category.get(position).getTitle(), true));
               //   holder.answerText.setText(simpleBot.sayInReturn(category.get(position).getTitle(), true));
               // MainActivity.showCursesByActivity(category.get(position).getId());
                System.out.println(category.get(position).getTitle());  // при нажатии на каждую категорию открывается своя категория
            }                                   //category.get(position).getId() получаем Id каждого курса, из списка(список пронумерован 123 берём число это и есть id)

        });*/

    }

    @Override
    public int getItemCount() {

        return category.size();
    }




    public static final class categoryVew extends RecyclerView.ViewHolder {   ///    final  - значит класс не имеет наследников

      //  public  CardView card_pertanyaan;
      CardView card_pertanyaan;
      TextView CattextView;

       // EditText answerText;



        public categoryVew(@NonNull View itemView) {
            super(itemView);
            card_pertanyaan = (CardView) itemView.findViewById(R.id.card_pertanyaan);
            CattextView = (TextView) itemView.findViewById(R.id.CattextView);
            //answerText = (EditText) itemView.findViewById(R.id.answerText);

            //answerText = new HomeFragment().getView().findViewById(R.id.answerText);


            //new HomeFragment().getActivity().getSupportFragmentManager().findFragmentById(R.id.answerText);
           // answerText = (EditText) itemView.findViewById(R.id.answerText);
       // CattextView = itemView.findViewById(R.id.CattextView);

      //  card_pertanyaan = itemView.findViewById(R.id.card_pertanyaan);
    }



    }


}

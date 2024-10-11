package com.example.hofprog;

import static com.example.hofprog.MainActivity.ni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofprog.factory.ManagerViewModelFactory;
import com.example.hofprog.factory.NewViewModelFactory;
import com.example.hofprog.factory.ProgerrViewModelFactory;
import com.example.hofprog.factory.WhoiViewModelFactory;
import com.example.hofprog.model.whoi;
import com.example.hofprog.repository.ManageRepository;
import com.example.hofprog.repository.NewRepository;
import com.example.hofprog.repository.ProgerRepository;
import com.example.hofprog.repository.WhoiRepository;
import com.example.hofprog.viewmodel.ManagerViewModel;
import com.example.hofprog.viewmodel.NewViewModel;
import com.example.hofprog.viewmodel.OldViewModel;
import com.example.hofprog.viewmodel.ProgerViewModel;
import com.example.hofprog.viewmodel.WhoiViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder> {
    static ArrayList<String> dat;
    static ArrayList<String> st;
    static String nick;
    static ArrayList<String> opisanie;
    private final Context context;

    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private OldViewModel oldViewModel;
    private ProgerViewModel progerViewModel;
    public AdapterTask(Context context, ArrayList<String> st, ArrayList<String> dat, ArrayList<String> opisanie, String nick, WhoiViewModel whoiViewModel, NewViewModel newViewModel, OldViewModel oldViewModel, ProgerViewModel progerViewModel, ManagerViewModel managerViewModel) {
        this.context = context;
        this.st = st;
        this.dat = dat;
        this.opisanie = opisanie;
        this.nick = nick;
        this.whoiViewModel = whoiViewModel;
        this.newViewModel= newViewModel;
        this.oldViewModel = oldViewModel;
        this.progerViewModel = progerViewModel;
        this.managerViewModel = managerViewModel;
    }
    @NonNull
    @Override
    public AdapterTask.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_ex, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTask.ViewHolder holder, int position) {
        int y = position;
        final boolean[] fqw = {true};
        new Thread(() -> {
            int abba = whoiViewModel.findAll(ni);
            System.out.println(abba);
            holder.ordinalNumberTextView.setText(String.valueOf(y + 1) + ". " + st.get(y));
            if (abba == 0) {
                if (st.size() > 0 && !Objects.equals(st.get(0), "") && Objects.equals(dat.get(y).split(" ")[1], "0")) {
                    holder.itemContentTextView.setText(String.valueOf(dat.get(y).split(" ")[0]));
                    // Получаем текущую дату
                    Calendar today = Calendar.getInstance();
                    today.set(Calendar.HOUR_OF_DAY, 0);
                    today.set(Calendar.MINUTE, 0);
                    today.set(Calendar.SECOND, 0);
                    today.set(Calendar.SECOND, 0);
                    Calendar y_zad = Calendar.getInstance();
                    String[] arr = dat.get(y).split(" ")[0].split("\\.");
                    y_zad.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
                    y_zad.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arr[0]));
                    y_zad.set(Calendar.YEAR, Integer.parseInt(arr[2]));
                    // Вычисляем разницу
                    long diffInMillis = y_zad.getTimeInMillis() - today.getTimeInMillis();
                    long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
                    // Проверяем, что разница меньше недели
                    if (diffInDays < 0) {
                        holder.itemImageView.setBackgroundColor(Color.BLACK);
                    } else if (diffInDays < 7) {
                        holder.itemImageView.setBackgroundColor(Color.RED);
                    } else if (diffInDays < 14) {
                        holder.itemImageView.setBackgroundColor(Color.YELLOW);
                    } else {
                        holder.itemImageView.setBackgroundColor(Color.GREEN);
                    }
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Opis_task.class);
                        intent.putExtra("y", y);
                        intent.putExtra("nick", nick);
                        context.startActivity(intent);
                    }
                });
            } else {
                if (st.size() > 0 && !Objects.equals(st.get(0), "")/* && Objects.equals(dat.get(y).split(" ")[1], "1")*/) {
                    holder.itemContentTextView.setText(String.valueOf(dat.get(y).split(" ")[0]));
                    // Получаем текущую дату
                    Calendar today = Calendar.getInstance();
                    today.set(Calendar.HOUR_OF_DAY, 0);
                    today.set(Calendar.MINUTE, 0);
                    today.set(Calendar.SECOND, 0);
                    today.set(Calendar.SECOND, 0);
                    Calendar y_zad = Calendar.getInstance();
                    String[] arr = dat.get(y).split(" ")[0].split("\\.");
                    y_zad.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
                    y_zad.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arr[0]));
                    y_zad.set(Calendar.YEAR, Integer.parseInt(arr[2]));
                    // Вычисляем разницу
                    long diffInMillis = y_zad.getTimeInMillis() - today.getTimeInMillis();
                    long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
                    // Проверяем, что разница меньше недели
                    if (diffInDays < 0) {
                        holder.itemImageView.setBackgroundColor(Color.BLACK);
                    } else if (diffInDays < 7) {
                        holder.itemImageView.setBackgroundColor(Color.RED);
                    } else if (diffInDays < 14) {
                        holder.itemImageView.setBackgroundColor(Color.YELLOW);
                    } else {
                        holder.itemImageView.setBackgroundColor(Color.GREEN);
                    }
                    ((Activity) context).runOnUiThread(() -> {
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, Opis_task.class);
                                intent.putExtra("y", y);
                                intent.putExtra("nick", nick);
                                context.startActivity(intent);
                            }
                        });
                    });
                }//change
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return st.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ordinalNumberTextView;
        TextView itemContentTextView;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordinalNumberTextView = itemView.findViewById(R.id.name);
            itemContentTextView = itemView.findViewById(R.id.value);
            itemImageView = itemView.findViewById(R.id.picture);
        }
    }
}
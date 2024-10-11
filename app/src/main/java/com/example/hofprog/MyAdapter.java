package com.example.hofprog;

import static android.content.Intent.getIntent;
import static com.example.hofprog.MainActivity.ni;
import static com.example.hofprog.ex.f;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofprog.model.newtask;
import com.example.hofprog.model.oldtask;
import com.example.hofprog.model.proger;
import com.example.hofprog.viewmodel.ManagerViewModel;
import com.example.hofprog.viewmodel.NewViewModel;
import com.example.hofprog.viewmodel.OldViewModel;
import com.example.hofprog.viewmodel.ProgerViewModel;
import com.example.hofprog.viewmodel.WhoiViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final ArrayList<String> st;
    private final ArrayList<String> fio;
    private final ArrayList<String> pas;
    private final String flag;
    private final Context context;
    private static int id_progr;
    private List<proger> userList = new ArrayList<>();


    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private OldViewModel oldViewModel;
    private ProgerViewModel progerViewModel;
    public MyAdapter(Context context, ArrayList<String> st, ArrayList<String> fio, ArrayList<String> pas, String flag, WhoiViewModel whoiViewModel, NewViewModel newViewModel, ProgerViewModel progerViewModel, ManagerViewModel managerViewModel, OldViewModel oldViewModel) {
        this.context = context;
        this.st = st;
        this.pas = pas;
        this.fio = fio;
        this.flag = flag;
        this.whoiViewModel = whoiViewModel;
        this.newViewModel= newViewModel;
        this.progerViewModel = progerViewModel;
        this.managerViewModel = managerViewModel;
        this.oldViewModel = oldViewModel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_progger, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Intent intent = ((Activity) context).getIntent(); // Получаем Intent из активности
        String yy = intent.getStringExtra("opop");
        String progeri = intent.getStringExtra("st");
        String p = intent.getStringExtra("atat");
        String d = intent.getStringExtra("dat");
        int y = position;
        holder.ordinalNumberTextView.setText(String.valueOf(position + 1));
        holder.ordinalNumberTextView.setText(String.valueOf(y + 1) + ". " + fio.get(y));
        holder.passwd.setText("Пароль: " + pas.get(y));
        holder.login.setText("Логин: " + st.get(y));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(flag+" FLAG");
                if (!Objects.equals(flag, null)) {
                    LiveData<List<newtask>> task = newViewModel.getAllUsers();
                    task.observe((LifecycleOwner) context, tsList -> {
                        for (newtask tas : tsList) {
                            if (tas.getFor_who().equals(ni) && Objects.equals(tas.getName().substring(0, tas.getName().lastIndexOf(' ')), flag)) {
                                newViewModel.updateById(tas.getNew_id());
                            }
                        }
                    });

                    new Thread(() -> {
                        oldtask tsk = new oldtask(holder.login.getText().toString().substring(holder.login.getText().toString().indexOf(' ')+1), yy+" "+d, p);
                        long idd = oldViewModel.insert(tsk);
                        System.out.println(tsk.toString());
                        tsk.setOld_id((int) idd);
                        ((Activity) context).runOnUiThread(() -> {
                            Intent intent = new Intent(context, ForMan.class);
                            System.out.println(ni+"  nii");
                            context.startActivity(intent);
                        });
                    }).start();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return st.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ordinalNumberTextView;
        TextView login;
        TextView passwd;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ordinalNumberTextView = itemView.findViewById(R.id.user_name_text);
            itemImageView = itemView.findViewById(R.id.user_avatar_image);
            login = itemView.findViewById(R.id.login);
            passwd = itemView.findViewById(R.id.passwd);
        }
    }
}
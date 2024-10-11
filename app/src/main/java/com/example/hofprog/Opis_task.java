package com.example.hofprog;

import static com.example.hofprog.AdapterTask.dat;
import static com.example.hofprog.AdapterTask.opisanie;
import static com.example.hofprog.AdapterTask.st;
import static com.example.hofprog.MainActivity.ni;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hofprog.factory.ManagerViewModelFactory;
import com.example.hofprog.factory.NewViewModelFactory;
import com.example.hofprog.factory.OldViewModelFactory;
import com.example.hofprog.factory.ProgerrViewModelFactory;
import com.example.hofprog.factory.WhoiViewModelFactory;
import com.example.hofprog.model.manage;
import com.example.hofprog.model.newtask;
import com.example.hofprog.model.oldtask;
import com.example.hofprog.model.proger;
import com.example.hofprog.model.whoi;
import com.example.hofprog.repository.ManageRepository;
import com.example.hofprog.repository.NewRepository;
import com.example.hofprog.repository.OldRepository;
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
public class Opis_task extends AppCompatActivity {
    public static String progerri = "";

    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private OldViewModel oldViewModel;
    private ProgerViewModel progerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ManageRepository mrepository = new ManageRepository(getApplication()); // Инициализация репозитория
        ManagerViewModelFactory mfactory = new ManagerViewModelFactory(mrepository);
        managerViewModel = new ViewModelProvider(this, mfactory).get(ManagerViewModel.class);

        WhoiRepository wrepository = new WhoiRepository(getApplication()); // Инициализация репозитория
        WhoiViewModelFactory wfactory = new WhoiViewModelFactory(wrepository);
        whoiViewModel = new ViewModelProvider(this, wfactory).get(WhoiViewModel.class);

        ProgerRepository prepository = new ProgerRepository(getApplication()); // Инициализация репозитория
        ProgerrViewModelFactory pfactory = new ProgerrViewModelFactory(prepository);
        progerViewModel = new ViewModelProvider(this, pfactory).get(ProgerViewModel.class);

        NewRepository nrepository = new NewRepository(getApplication()); // Инициализация репозитория
        NewViewModelFactory nfactory = new NewViewModelFactory(nrepository);
        newViewModel = new ViewModelProvider(this, nfactory).get(NewViewModel.class);

        OldRepository orepository = new OldRepository(getApplication()); // Инициализация репозитория
        OldViewModelFactory ofactory = new OldViewModelFactory(orepository);
        oldViewModel = new ViewModelProvider(this, ofactory).get(OldViewModel.class);

        setContentView(R.layout.activity_opis_task);
        int y = getIntent().getIntExtra("y", -1);
        Intent intent = getIntent();
        String nick = intent.getStringExtra("nick");
        TextView num_z = findViewById(R.id.Number);
        TextView nam = findViewById(R.id.Name);
        TextView srok = findViewById(R.id.Srok);
        ImageView fon = findViewById(R.id.Fon);
        TextView opis = findViewById(R.id.Opis);
        Button exit = findViewById(R.id.Exit);
        LinearLayout lil = findViewById(R.id.LiL);
        LinearLayout pp = findViewById(R.id.pp);
        pp.setVisibility(View.VISIBLE);
        lil.setVisibility(View.GONE);
        num_z.setText("Зaдача №" + (y + 1));
        System.out.println(15);
        nam.setText(st.get(y));
        opis.setText(opisanie.get(y));
        srok.setText(dat.get(y).split(" ")[0]);
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
            fon.setBackgroundColor(Color.BLACK);
        } else if (diffInDays < 7) {
            fon.setBackgroundColor(Color.RED);
        } else if (diffInDays < 14) {
            fon.setBackgroundColor(Color.YELLOW);
        } else {
            fon.setBackgroundColor(Color.GREEN);
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button mn = findViewById(R.id.man);
        Button pr = findViewById(R.id.prog);
        System.out.println(16);
        new Thread(() -> {
            if (whoiViewModel.findAll(ni) == 1) {
                System.out.println(17);
                mn.setVisibility(View.GONE);//смотрим статус задачи
                System.out.println(oldViewModel.findAll(nam.getText().toString()+ " "+dat.get(y).split(" ")[0]));
                if (oldViewModel.findAll(nam.getText().toString()+ " "+dat.get(y).split(" ")[0]) != 1)
                    pr.setVisibility(View.GONE);
                System.out.println(18);
            }
            else
                pr.setVisibility(View.GONE);
        }).start();
        mn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<List<proger>> alm = progerViewModel.getAllUsers();
                alm.observe(Opis_task.this, alList -> {
                    for (proger msg : alList) {
                        if (msg.getWho_rab() != null && msg.getWho_rab().equals(ni)) {
                            progerri = msg.getWho_rab();
                            Intent intent = new Intent(Opis_task.this, ex.class);
                            intent.putExtra("st", progerri);
                            ArrayList<String> ar = new ArrayList<>(Arrays.asList(st.get(y), opisanie.get(y)));
                            System.out.println(Arrays.toString(ar.toArray()));
                            intent.putExtra("opop", st.get(y));
                            System.out.println(st +"SSSSTTTT");
                            intent.putExtra("atat", opisanie.get(y));
                            intent.putExtra("dat", dat.get(y).split(" ")[0]);
                            startActivity(intent);
                            break;
                        }
                    }
                });
            }
        });
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pp.setVisibility(View.GONE);
                lil.setVisibility(View.VISIBLE);
                EditText et = findViewById(R.id.ET1);
                TextView vx = findViewById(R.id.VX);
                final boolean[] f = {false};
                vx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f[0] = true;
                        et.setText("");
                        lil.setVisibility(View.GONE);
                        pp.setVisibility(View.VISIBLE);
                    }
                });
                if (f[0]) return;
                Button btt = findViewById(R.id.BTT);
                btt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(19);
                        LiveData<List<oldtask>> tsk = oldViewModel.getAllUsers();
                        tsk.observe(Opis_task.this, tsList -> {
                            for (oldtask ipp : tsList) {//по всем задачам
                                System.out.println(ipp.getName().substring(0, ipp.getName().lastIndexOf(' ')));
                                if (ipp.getName().substring(0, ipp.getName().lastIndexOf(' ')).equals(String.valueOf(nam.getText()))) {//название
                                    oldViewModel.updateById(ipp.getOld_id());
                                    Intent intent = new Intent(Opis_task.this, ForProg.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}

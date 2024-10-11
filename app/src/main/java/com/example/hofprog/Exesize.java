package com.example.hofprog;

import static com.example.hofprog.ForProg.f;
import static com.example.hofprog.MainActivity.ni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.example.hofprog.model.newtask;
import com.example.hofprog.model.oldtask;
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

public class Exesize extends AppCompatActivity {
    Button buton;
    LinearLayout ll;
    DatePicker datePicker;

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

        setContentView(R.layout.actovity_exersize);
        ArrayList<String> st = new ArrayList<>();
        ArrayList<String> dat = new ArrayList<>();
        Intent intent = getIntent();
        ArrayList<String> opisanie = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.RvV);
        if (f == 0) {//задачи менеджера
            LiveData<List<newtask>> t = newViewModel.getAllUsers();
            t.observe(Exesize.this, tList -> {
                for (newtask ipp : tList) {
                    if (Objects.equals(ipp.getFor_who(), ni) && ipp.getStat() == 0) {
                        st.add(ipp.getName().substring(0, ipp.getName().lastIndexOf(' ')));
                        dat.add(ipp.getName().substring(ipp.getName().lastIndexOf(' ')+1) + " " + ipp.getStat());
                        opisanie.add(ipp.getOpis());
                    }
                }
                updateRecyclerView(st, dat, opisanie, ni);
            });
        }
        else {
            if (f == 1) {//задачи программиста
                LiveData<List<oldtask>> t = oldViewModel.getAllUsers();
                t.observe(Exesize.this, tList -> {
                    for (oldtask ipp : tList) {
                        System.out.println(ipp.getFor_who());
                        if (Objects.equals(ipp.getFor_who(), ni) && ipp.getStat() == 1) {
                            st.add(ipp.getName().substring(0, ipp.getName().lastIndexOf(' ')));
                            System.out.println(ipp.getName().substring(ipp.getName().lastIndexOf(' ')+1) + " " + ipp.getStat());
                            dat.add(ipp.getName().substring(ipp.getName().lastIndexOf(' ')+1) + " " + ipp.getStat());
                            opisanie.add(ipp.getOpis());
                        }
                    }
                    updateRecyclerView(st, dat, opisanie, ni);
                });
            }
            else if (f == 2) {//сделанные задачи программиста
                LiveData<List<oldtask>> t = oldViewModel.getAllUsers();
                t.observe(Exesize.this, tList -> {
                    for (oldtask ipp : tList) {
                        if (Objects.equals(ipp.getFor_who(), ni) && ipp.getStat() == 2) {
                            st.add(ipp.getName().substring(0, ipp.getName().lastIndexOf(' ')));
                            dat.add(ipp.getSroki() + " " + ipp.getStat());
                            opisanie.add(ipp.getOpis());
                        }
                    }
                    updateRecyclerView(st, dat, opisanie, ni);
                });
            }
        }
        ll = findViewById(R.id.LiL1);
        ll.setVisibility(View.GONE);
        datePicker = findViewById(R.id.DT);
        buton = findViewById(R.id.new_task);
        if (f != 0)
            buton.setVisibility(View.GONE);
        Button bt = findViewById(R.id.Back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<List<whoi>> uu = whoiViewModel.getAllUsers();
                uu.observe(Exesize.this, uuList -> {
                    for (whoi u : uuList) {
                        System.out.println(u.getNick());
                        if (u.getNick().equals(ni))
                            if (u.getProg() == 1) {
                                Intent intent = new Intent(Exesize.this, ForProg.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                Intent intent = new Intent(Exesize.this, ForMan.class);
                                startActivity(intent);
                                finishAffinity();
                            }
                    }
                });
            }
        });
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                buton.setVisibility(View.GONE);
                TextView vx = findViewById(R.id.VX);
                final boolean[] f = {false};
                vx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f[0] = true;
                        ll.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        buton.setVisibility(View.VISIBLE);
                    }
                });
                if (f[0]) return;
                EditText et1 = findViewById(R.id.ET1);
                EditText et2 = findViewById(R.id.ET2);
                Button button = findViewById(R.id.BTT);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                            Toast.makeText(Exesize.this, "Введите данные во все поля", Toast.LENGTH_SHORT).show();
                        } else {
                            final String[] data = {""};
                            // Получаем календарь и устанавливаем его на 2024 год
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                            calendar.set(Calendar.MINUTE, 0);
                            calendar.set(Calendar.SECOND, 0);
                            calendar.set(Calendar.MILLISECOND, 0);
                            datePicker.setMinDate(calendar.getTimeInMillis());
                            // Устанавливаем максимальную дату (на 5 лет вперед от текущей даты)
                            calendar.add(Calendar.YEAR, 5);
                            datePicker.setMaxDate(calendar.getTimeInMillis());
                            data[0] = datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear();
                            if (Objects.equals(data[0], ""))
                                Toast.makeText(Exesize.this, "Выберите дату", Toast.LENGTH_SHORT).show();
                            else {
                                newtask tac = new newtask(ni + " " + data[0], et1.getText().toString(), et2.getText().toString());
                                long ii = newViewModel.insert(tac);
                                tac.setNew_id((int) ii);
                            }
                        }
                    }
                });
            }
        });
    }

    private void updateRecyclerView (ArrayList <String> st, ArrayList <String> dat, ArrayList < String > opisanie, String ni){
        if (st.size() > 0) {
            while (Objects.equals(st.get(0), " ") || Objects.equals(st.get(0), ""))
                st.remove(0);
        }
        RecyclerView recyclerView = findViewById(R.id.RvV);
        recyclerView.setLayoutManager(new LinearLayoutManager(Exesize.this));
        AdapterTask adapter = new AdapterTask(Exesize.this, st, dat, opisanie, ni, whoiViewModel, newViewModel, oldViewModel, progerViewModel, managerViewModel);
        recyclerView.setAdapter(adapter);
    }
}

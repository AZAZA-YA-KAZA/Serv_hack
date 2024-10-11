package com.example.hofprog;

import static com.example.hofprog.Opis_task.progerri;

import android.content.Intent;
import android.os.Bundle;

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
import com.example.hofprog.model.proger;
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
import java.util.List;
import java.util.Objects;
public class ex extends AppCompatActivity {
    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private ProgerViewModel progerViewModel;
    private OldViewModel oldViewModel;
    static boolean f = false;
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

        setContentView(R.layout.activity_ex);
        Intent intent = getIntent();
        String y = intent.getStringExtra("opop");
        String progeri = intent.getStringExtra("st");
        String p = intent.getStringExtra("atat");
        String d = intent.getStringExtra("dat");
        System.out.println(y+"YYY"+p);
        ArrayList<String> st = new ArrayList<>();
        ArrayList<String> fio = new ArrayList<>();
        ArrayList<String> pas = new ArrayList<>();
        LiveData<List<proger>> al = progerViewModel.getAllUsers();
        al.observe(ex.this, alList -> {
            for (proger pr : alList) {
                System.out.println(progeri);
                if (Objects.equals(pr.getWho_rab(), progeri)) {
                    st.add(pr.getLogin());
                    fio.add(pr.getFio());
                    pas.add(pr.getPassword());
                }
            }
            RecyclerView recyclerView = findViewById(R.id.RV);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            System.out.println(st + " ST");
            MyAdapter adapter = new MyAdapter(this, st, fio, pas, y, whoiViewModel, newViewModel, progerViewModel, managerViewModel, oldViewModel);
            recyclerView.setAdapter(adapter);

        });
    }
}


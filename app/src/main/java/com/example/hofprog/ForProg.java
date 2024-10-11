package com.example.hofprog;

import static com.example.hofprog.MainActivity.mypreference;
import static com.example.hofprog.MainActivity.ni;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.factory.ManagerViewModelFactory;
import com.example.hofprog.factory.NewViewModelFactory;
import com.example.hofprog.factory.ProgerrViewModelFactory;
import com.example.hofprog.factory.WhoiViewModelFactory;
import com.example.hofprog.model.manage;
import com.example.hofprog.model.proger;
import com.example.hofprog.repository.ManageRepository;
import com.example.hofprog.repository.NewRepository;
import com.example.hofprog.repository.ProgerRepository;
import com.example.hofprog.repository.WhoiRepository;
import com.example.hofprog.viewmodel.ManagerViewModel;
import com.example.hofprog.viewmodel.NewViewModel;
import com.example.hofprog.viewmodel.ProgerViewModel;
import com.example.hofprog.viewmodel.WhoiViewModel;

import java.util.List;
import java.util.Objects;
public class ForProg extends AppCompatActivity {
    static int f = 0;
    public static String nick;
    TextView rar;
    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
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

        setContentView(R.layout.activity_forprog);
        Button exit = findViewById(R.id.Exit);
        rar = findViewById(R.id.rar);
        Button bt = findViewById(R.id.BT);
        Button tec = findViewById(R.id.tec);
        Intent intent = getIntent();
        String ni = intent.getStringExtra("ni");
        tec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = 1;
                Intent intent = new Intent(ForProg.this, Exesize.class);
                intent.putExtra("ni", ni);
                startActivity(intent);
            }
        });
        Button otpr = findViewById(R.id.otpr);
        otpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = 2;
                System.out.println(ni+ "  NII");
                Intent intent = new Intent(ForProg.this, Exesize.class);
                intent.putExtra("ni", ni);
                startActivity(intent);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:89193462701"));
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply(); // или editor.commit();
                finishAffinity();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        nick = ni;
        System.out.println(ni);
        progerViewModel.findUserById(nick).observe(this, new Observer<proger>() {
            @Override
            public void onChanged(proger fio) {
                System.out.println(fio.toString());
                if (fio.getFio() != null) {
                    // Здесь вы можете использовать значение fio
                    String bob = fio.getFio(); // Теперь bob содержит строку
                    rar.setText(bob); // Пример использования
                } else {
                    rar.setText("Пользователь не найден");
                }
            }
        });
    }
}

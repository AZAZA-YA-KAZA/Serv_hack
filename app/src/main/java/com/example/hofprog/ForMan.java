package com.example.hofprog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.hofprog.MainActivity.decrypt;
import static com.example.hofprog.MainActivity.encrypt;
import static com.example.hofprog.MainActivity.id;
import static com.example.hofprog.MainActivity.ni;
import static com.example.hofprog.ForProg.f;
import static com.example.hofprog.MainActivity.mypreference;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hofprog.factory.ManagerViewModelFactory;
import com.example.hofprog.factory.NewViewModelFactory;
import com.example.hofprog.factory.ProgerrViewModelFactory;
import com.example.hofprog.factory.WhoiViewModelFactory;
import com.example.hofprog.model.manage;
import com.example.hofprog.model.proger;
import com.example.hofprog.model.whoi;
import com.example.hofprog.repository.ManageRepository;
import com.example.hofprog.repository.NewRepository;
import com.example.hofprog.repository.ProgerRepository;
import com.example.hofprog.repository.WhoiRepository;
import com.example.hofprog.viewmodel.ManagerViewModel;
import com.example.hofprog.viewmodel.NewViewModel;
import com.example.hofprog.viewmodel.ProgerViewModel;
import com.example.hofprog.viewmodel.WhoiViewModel;

import java.util.List;

public class ForMan extends AppCompatActivity {
    public static String progeri = "";
    public static String nick;
    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private ProgerViewModel progerViewModel;
    TextView nm;
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

        setContentView(R.layout.activity_forman);
        LinearLayout linearLayout1 = findViewById(R.id.LL1);
        LinearLayout linearLayout2 = findViewById(R.id.LL2);
        nm = findViewById(R.id.Name);
        linearLayout1.setVisibility(View.GONE);
        Button exit = findViewById(R.id.Exit);
        Button sot = findViewById(R.id.Sot);
        Button bt = findViewById(R.id.BT);
        Intent intent = getIntent();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:89969903171"));
                startActivity(intent);
            }
        });
        Button prognew = findViewById(R.id.prognew);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                String storedValue = sharedpreferences.getString(id, "ghjk");  // Получаем значение как целое число
                finishAffinity();
            }
        });
        sot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<List<proger>> alm = progerViewModel.getAllUsers();
                alm.observe(ForMan.this, prList -> {
                    Intent intent = new Intent(ForMan.this, ex.class);
                    boolean ff = true;
                    for (proger pr : prList) {
                        System.out.println(pr.getWho_rab()+" u "+ni);
                        if (pr.getWho_rab() != null && pr.getWho_rab().equals(ni)) {
                            ff = false;
                            progeri = pr.getWho_rab();
                            intent.putExtra("st", progeri);
                            startActivity(intent);
                            break;
                        }
                    }
                    if (ff)
                        Toast.makeText(ForMan.this, "У вас пока нет сотрудников", Toast.LENGTH_SHORT).show();
                });
            }
        });
        prognew.setOnClickListener(new View.OnClickListener() {//в список
            @Override
            public void onClick(View v) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                TextView vx = findViewById(R.id.VX);
                final boolean[] f = {false};
                vx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        f[0] = true;
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.VISIBLE);
                    }
                });
                if (f[0]) return;
                EditText et1 = findViewById(R.id.ET1);
                EditText et2 = findViewById(R.id.ET2);
                EditText et3 = findViewById(R.id.ET3);
                EditText et4 = findViewById(R.id.ET4);
                Button button = findViewById(R.id.BTT);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et1.getText().toString().equals("") || et2.getText().toString().equals("") || et3.getText().toString().equals("") || et4.getText().toString().equals("")) {
                            Toast.makeText(ForMan.this, "Введите данные во все поля", Toast.LENGTH_SHORT).show();
                        } else {
                            LiveData<List<manage>> allManag = managerViewModel.getAllUsers();
                            new Thread(() -> {
                                if (managerViewModel.countUsersByNamecountUsersByName(et2.getText().toString()) != 0 || progerViewModel.countUsersByNamecountUsersByName(et2.getText().toString()) != 0) {
                                    runOnUiThread(()-> {
                                    et2.setText("");
                                    Toast.makeText(ForMan.this, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                                });
                                } else {
                                    String encryptedData = null;
                                    String encryptedData1 = null;
                                    try {
                                        encryptedData = encrypt(et2.getText().toString());
                                        encryptedData1 = encrypt(et3.getText().toString());
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    String finalEncryptedData = encryptedData;
                                    String finalEncryptedData1 = encryptedData;
                                    whoi who = new whoi(encryptedData, 0, 1);
                                    long idi = whoiViewModel.insert(who);
                                    who.setId((int) idi);
                                    proger prog = new proger(et1.getText().toString(), encryptedData,
                                            encryptedData1, et4.getText().toString(), ni);
                                    idi = progerViewModel.insert(prog);
                                    prog.setProg_id((int) idi);
                                    runOnUiThread(()-> {
                                        Toast.makeText(ForMan.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                                        et1.setText("");
                                        et2.setText("");
                                        et3.setText("");
                                        et4.setText("");
                                        linearLayout2.setVisibility(View.VISIBLE);
                                        linearLayout1.setVisibility(View.GONE);
                                    });
                                }
                            }).start();
                        }
                    }
                });
            }
        });
        Button exesize = findViewById(R.id.Exesize);
        exesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = 0;
                Intent intent = new Intent(ForMan.this, Exesize.class);
                intent.putExtra("ni", nick);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        nick = ni;
        managerViewModel.findUserById(nick).observe(this, new Observer<manage>() {
            @Override
            public void onChanged(manage fio) {
                if (fio.getFio() != null) {
                    // Здесь вы можете использовать значение fio
                    String bob = fio.getFio(); // Теперь bob содержит строку
                    nm.setText(bob); // Пример использования
                } else {
                    nm.setText("Пользователь не найден");
                }
            }
        });
    }
}
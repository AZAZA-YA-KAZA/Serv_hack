package com.example.hofprog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hofprog.factory.ManagerViewModelFactory;
import com.example.hofprog.factory.NewViewModelFactory;
import com.example.hofprog.factory.ProgerrViewModelFactory;
import com.example.hofprog.factory.WhoiViewModelFactory;
import com.example.hofprog.model.manage;
import com.example.hofprog.model.newtask;
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

import java.util.Base64;
import java.util.List;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MainActivity extends AppCompatActivity {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String progeri = "";
    public static final String mypreference = "mypref";
    public static final String id = null;//может другое
    public static String ni = "132";
    public static int i = 132;
    private ManagerViewModel managerViewModel;
    private WhoiViewModel whoiViewModel;
    private NewViewModel newViewModel;
    private ProgerViewModel progerViewModel;// Генерация ключа
    private SecretKey secretKey = generateKey();
    // Вектор инициализации (IV) - должен быть случайным и уникальным для каждого шифрования
    byte[] iv = new byte[16]; // AES использует размер блока 16 байт

    public MainActivity() throws Exception {
    }

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

        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.FN);
        LinearLayout linearLayout1 = findViewById(R.id.LL1);
        LinearLayout linearLayout2 = findViewById(R.id.LL2);
        FrameLayout frameLayout = findViewById(R.id.FL);
        frameLayout.setVisibility(View.GONE);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        Button button1 = findViewById(R.id.BT1);
        Button button2 = findViewById(R.id.BT2);
        SharedPreferences sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        String storedValue = sharedpreferences.getString(id, "ghjk");  // Получаем значение как целое число
        if (!storedValue.equals("ghjk")) {
            LiveData<List<whoi>> allw = whoiViewModel.getAllUsers();
            allw.observe(this, whoiList -> {
                for (whoi it : whoiList) {
                    System.out.println(storedValue+ it.getNick());
                    if (Objects.equals(it.getNick(), storedValue)) {
                        ni = storedValue;
                        System.out.println("aaa");
                        if (it.getMan() == 1) {
                            Intent intent = new Intent(MainActivity.this, ForMan.class);
                            intent.putExtra("ni", ni);
                            startActivity(intent);
                            return;
                        } else {
                            Intent intent = new Intent(MainActivity.this, ForProg.class);
                            intent.putExtra("ni", ni);
                            startActivity(intent);
                            return;
                        }
                    }
                }
            });
        }
        else {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    linearLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//нужна проверка есть ли пользователь в базе
                            vxod();
                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reg();
                        }
                    });
                }

                EditText et1;
                EditText et2;

                private void reg() {
                    frameLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    et1 = findViewById(R.id.ET1);
                    et2 = findViewById(R.id.ET2);
                    EditText et3 = findViewById(R.id.ET3);
                    EditText et4 = findViewById(R.id.ET4);
                    Button button = findViewById(R.id.BTT);
                    TextView tv = findViewById(R.id.TV3);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vxod();
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (et1.getText().toString().equals("") || et2.getText().toString().equals("") || et3.getText().toString().equals("") || et4.getText().toString().equals("")) {
                                Toast.makeText(MainActivity.this, "Введите данные во все поля", Toast.LENGTH_SHORT).show();
                            } else {
                                String idToCheck = et2.getText().toString();
                                try {
                                    String dataToEncrypt = idToCheck;
                                    new java.security.SecureRandom().nextBytes(iv);
                                    // Шифрование данных
                                    String encryptedData = encrypt(dataToEncrypt, secretKey, iv);
                                    String encryptedData1 = encrypt(et3.getText().toString(), secretKey, iv);
                                    System.out.println("Зашифрованные данные: " + encryptedData);
                                    // Расшифровка данных
                                    String decryptedData = decrypt(encryptedData, secretKey, iv);
                                    System.out.println("Расшифрованные данные: " + decryptedData);
                                    new Thread(() -> {
                                        if (managerViewModel.countUsersByNamecountUsersByName(encryptedData) != 0) {
                                            et1.setText("");
                                            Toast.makeText(MainActivity.this, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show();
                                        } else {
                                            manage messag = new manage(et1.getText().toString(), encryptedData, encryptedData1, et4.getText().toString());
                                            long newId = managerViewModel.insert(messag);// Здесь id будет сгенерирован
                                            messag.setId((int) newId);
                                            whoi who = new whoi(encryptedData, 1, 0);
                                            newId = whoiViewModel.insert(who);
                                            who.setId((int) newId);
                                            ni = messag.getLogin();
                                            newtask task = new newtask(messag.getLogin(), "Львы 8.08.2024", "У дерева 4 льва, один ушёл... Расчитайте на какое расстояние он ушёл, применив формулу Лопиталя.");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Пингвины 30.04.2024", "Летели под землёй пингвины. Найти скорость, к соторой велосипед вырабатывал фотосинтез.");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Мышь 28.05.2024", "Мышь считала дырки в сыре, 3 + 2.. С какой вероятностью первое слово ребёнка будет попа?");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Тучки 1.05.2024", "Плыли по небу тучки, тучек.. Расчитать максимальное количество туч, которое может появиться на планете.");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Пентагон 8.08.2028", "Взломать Пентагон");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Шахматы 24.04.2025", "Выиграть шахмантого бота на уровне профи");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Чип 7.05.2024", "Создать чип, вживляющийся в человеческий мозг для вкачивания английского языка");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Тормоз 7.02.2024", "Написать программу для вычисления самого медленно работающего процессора в кабинете");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Полёт 5.05.2025", "Написать программу, моделирующую полёт человека с крыльями");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Паспорт 3.12.2024", "Смоделировать канадский паспорт с флагом РФ");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Марс 14.06.2024", "Смоделировать полёт на Марс");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Конец_света 14.05.2024", "Спрогнозировать конец света");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Курсовая_01 23.05.2024", "Написать курсовую работу Варваре Ерховой");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Курсовая_02 2.06.2024", "Защитить курсовую работу Ерховой Варваре");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            task = new newtask(messag.getLogin(), "Учебный_план 14.07.2024", "На основе поведения подростка спроектировать наиболее эффективный план по обучению ребёнка.");
                                            newId = newViewModel.insert(task);
                                            task.setNew_id((int) newId);
                                            // Скрытие полей ввода
                                            runOnUiThread(() -> {
                                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                                editor.putString(id, messag.getLogin().toString());
                                                editor.apply();
                                                ni = messag.getLogin();
                                                // Переход на другую активность
                                                Intent intent = new Intent(MainActivity.this, ForMan.class);
                                                intent.putExtra("mypreference", mypreference);
                                                intent.putExtra("ni", ni);
                                                startActivity(intent);
                                                linearLayout.setVisibility(View.GONE);
                                                linearLayout1.setVisibility(View.GONE);
                                            });
                                        }
                                    }).start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }

                private void vxod() {
                    frameLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    EditText et1 = findViewById(R.id.ETT1);
                    EditText et2 = findViewById(R.id.ETT2);
                    Button button = findViewById(R.id.BT);
                    TextView tv = findViewById(R.id.TV2);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reg();
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (et1.getText().toString().equals("") || et2.getText().toString().equals("")) {
                                Toast.makeText(MainActivity.this, "Введите данные во все поля", Toast.LENGTH_SHORT).show();
                            } else {
                                new Thread(() -> {
                                    if (managerViewModel.findAll(et1.getText().toString(), et2.getText().toString()) != 0) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(MainActivity.this, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show();
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(id, et1.getText().toString());
                                            editor.apply();
                                            String storedValue = sharedpreferences.getString(id, "mannn");
                                            Intent intent = new Intent(MainActivity.this, ForMan.class);
                                            intent.putExtra("mypreference", mypreference);
                                            ni = et1.getText().toString();
                                            intent.putExtra("nick", ni);
                                            startActivity(intent);
                                            et1.setText("");
                                            i++;
                                            et2.setText("");
                                        });
                                    } else if (progerViewModel.findAll(et1.getText().toString(), et2.getText().toString()) != 0) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(MainActivity.this, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show();//отдельно для программистов сделать
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(id, et1.getText().toString());
                                            editor.apply();
                                            Intent intent = new Intent(MainActivity.this, ForProg.class);
                                            intent.putExtra("mypreference", mypreference);
                                            ni = et1.getText().toString();
                                            intent.putExtra("nick", ni);
                                            startActivity(intent);
                                            et1.setText("");
                                            et2.setText("");
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            Toast.makeText(MainActivity.this, "Такого пользователя не существует", Toast.LENGTH_SHORT).show();
                                            et1.setText("");
                                            et2.setText("");
                                        });
                                    }
                                }).start();
                            }
                        }
                    });
                }
            });
        }
    }
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128); // или 256 бит в зависимости от используемой версии
        return keyGen.generateKey();
    }

    // Метод для шифрования
    public static String encrypt(String data, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Метод для расшифровки
    public static String decrypt(String encryptedData, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
        byte[] originalData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(originalData);
    }

}
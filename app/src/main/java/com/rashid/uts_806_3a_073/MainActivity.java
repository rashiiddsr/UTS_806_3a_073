package com.rashid.uts_806_3a_073;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Spinner tipeKamar;
EditText hariNginap;
Button proses, batal, keluar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipeKamar = findViewById(R.id.tipekamar);
        hariNginap = findViewById(R.id.hari);
        proses = findViewById(R.id.proses);
        batal = findViewById(R.id.batal);
        keluar = findViewById(R.id.keluar);

        proses.setOnClickListener(v -> {
            String IDKamar, tipenyaKamar, namaKamar;
            double tarifKamar, totalBayar, diskonAwal, hargaSementara, diskonTambah, hargaAkhir, biayaTambahan;
            int banyakHari;

            namaKamar = tipeKamar.getSelectedItem().toString();
            if (namaKamar.equals("Melati")) {
                IDKamar = "100_073";
                tipenyaKamar = "Presidential Suite";
                tarifKamar = 10073073;
            } else if (namaKamar.equals("Mawar")) {
                IDKamar = "101_073";
                tipenyaKamar = "Superior Room";
                tarifKamar = 8073073;
            } else if (namaKamar.equals("Anggrek")) {
                IDKamar = "102_073";
                tipenyaKamar = "Standard Room";
                tarifKamar = 1073073;
            } else {
                Toast toast = Toast.makeText(this, "Mohon memilih salah satu Tipe Kamar",Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if (hariNginap.getText().toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Mohon Masukkan Lama Menginap").setNegativeButton("Ok", null).create().show();
                return;
            }

            banyakHari = Integer.parseInt(hariNginap.getText().toString());
            totalBayar = tarifKamar * banyakHari;
            diskonAwal = totalBayar * 10/100;
            hargaSementara = totalBayar - diskonAwal;

            biayaTambahan = 0.0;

            if (tipenyaKamar.equals("Presidential Suite")) {
                diskonTambah = totalBayar * 73/40/100;
            } else if (tipenyaKamar.equals("Superior Room")) {
                diskonTambah = totalBayar * 73/20/100;
            } else {
                diskonTambah = totalBayar * 73/20/100;
            }

            hargaAkhir = hargaSementara - diskonTambah;

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String pesanAkhir = "ID Kamar : " + IDKamar +
                    "\nNama Kamar : " + namaKamar +
                    "\nTipe Kamar : " + tipenyaKamar +
                    "\nLama Nginap : " + banyakHari + " Hari"+
                    "\nTagihan : "+ totalBayar +
                    "\nBiaya Tambahan : " + biayaTambahan +
                    "\nDiskon Awal (10%) : " + diskonAwal +
                    "\nDiskon Tambahan : " + diskonTambah +
                    "\nTotal Bayar Akhir : " + hargaAkhir;
            builder.setMessage(pesanAkhir).setPositiveButton("Ok", null).create().show();
    });

        batal.setOnClickListener(v -> {
            hariNginap.setText(null);
            tipeKamar.setSelection(0, true);
        });

        keluar.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });


    }
}
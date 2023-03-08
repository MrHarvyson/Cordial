package com.josec.cordial.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.josec.cordial.models.Alumno;

import java.util.HashMap;
import java.util.Map;

public class AlumnoProvider {

    private CollectionReference mCollection;

    public AlumnoProvider(){
        mCollection = FirebaseFirestore.getInstance().collection("Alumnos");
    }

    public Task<DocumentSnapshot> getUser(String id){
        return mCollection.document(id).get();
    }

    public Task<Void> creacionAlumno(Alumno alumno){
        return mCollection.document(alumno.getId()).set(alumno);
    }

    public Task<Void> updateAlumno(Alumno alumno){
        Map<String,Object> map = new HashMap<>();
        map.put("nombre",alumno.getNombre());
        map.put("primerApellido",alumno.getPrimerApellido());
        map.put("segundoApellido",alumno.getSegundoApellido());
        return mCollection.document(alumno.getId()).update(map);
    }

}

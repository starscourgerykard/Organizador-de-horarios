package com.example.organizadorhorarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {
    private List<Materia> materias = new ArrayList<>();
    private OnMateriaClickListener listener;

    public interface OnMateriaClickListener {
        void onMateriaClick(Materia materia);
    }

    public HorarioAdapter(OnMateriaClickListener listener) {
        this.listener = listener;
    }

    public void actualizarMaterias(List<Materia> nuevasMaterias) {
        this.materias = nuevasMaterias;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_materia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Materia materia = materias.get(position);
        holder.bind(materia);
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreMateria, tvProfesor, tvSalonHorario, tvDia;

        ViewHolder(View itemView) {
            super(itemView);
            tvNombreMateria = itemView.findViewById(R.id.tvNombreMateria);
            tvProfesor = itemView.findViewById(R.id.tvProfesor);
            tvSalonHorario = itemView.findViewById(R.id.tvSalonHorario);
            tvDia = itemView.findViewById(R.id.tvDia);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMateriaClick(materias.get(getAdapterPosition()));
                }
            });
        }

        void bind(Materia materia) {
            tvNombreMateria.setText(materia.getNombre());
            tvProfesor.setText(materia.getProfesor());
            tvSalonHorario.setText(materia.getSalon() + " • " +
                    materia.getHoraInicio() + " - " + materia.getHoraFin());
            tvDia.setText(obtenerNombreDia(materia.getDiaSemana()));
        }

        private String obtenerNombreDia(int dia) {
            switch (dia) {
                case 1: return "LUN";
                case 2: return "MAR";
                case 3: return "MIE";
                case 4: return "JUE";
                case 5: return "VIE";
                default: return "N/A";
            }
        }
    }
}
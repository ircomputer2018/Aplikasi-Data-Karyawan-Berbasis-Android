package skripsi.iwan.kurniawan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class tunjangan extends RecyclerView.Adapter<tunjangan.ViewHolder>{

    private Context context;
    private List<UserTunjangan> tunjangan;
    // Declare Interface
    private static ClickListener clickListener;
    public tunjangan(Context context, List<UserTunjangan> tunjangan) {
        this.tunjangan = tunjangan;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_user_tunjangan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public int getItemCount() {
        return tunjangan.size();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserTunjangan userTunjangan= tunjangan.get(position);

        holder.jabatan.setText(userTunjangan.getJabatan());
        holder.tkeluarga.setText(userTunjangan.getTkeluarga());
        holder.tkesehatan.setText(userTunjangan.getTkesehatan());
        holder.ttransportasi.setText(userTunjangan.getTtransportasi());
        holder.tpendidikan.setText(userTunjangan.getTpendidikan());

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView jabatan, tkeluarga, tkesehatan, ttransportasi, tpendidikan;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            jabatan = (TextView) itemView.findViewById(R.id.text1);
            tkeluarga = (TextView) itemView.findViewById(R.id.text2);
            tkesehatan = (TextView) itemView.findViewById(R.id.text3);
            ttransportasi = (TextView) itemView.findViewById(R.id.text4);
            tpendidikan = (TextView) itemView.findViewById(R.id.text5);

        }
        // Untuk menghandle event onclick
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
        // Untuk menghandle event onlongclick
        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        skripsi.iwan.kurniawan.tunjangan.clickListener = clickListener;
    }
    // Interface
    public interface ClickListener {
        void onItemClick(final int position, View v);
        void onItemLongClick(int position, View v);
    }
}
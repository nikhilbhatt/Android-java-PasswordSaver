package com.example.passwordsaver;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class adapterclass extends RecyclerView.Adapter<adapterclass.Examplewviewholder> {

        private Context mcontext;
        private Cursor mcursor;
        public adapterclass(Context context, Cursor cursor)
        {
            mcontext=context;
            mcursor=cursor;
        }
        public static class Examplewviewholder extends RecyclerView.ViewHolder {
        public TextView mtextview1;
        public TextView mtextview2;
        public TextView mtextview3;
        public Examplewviewholder(View itemView) {
            super(itemView);
            mtextview1=itemView.findViewById(R.id.textviewtittle);
            mtextview2=itemView.findViewById(R.id.textviewdesription);
            mtextview3=itemView.findViewById(R.id.examplename);
        }
    }
    @Override
    public Examplewviewholder onCreateViewHolder(ViewGroup parent, int i) {

            LayoutInflater inflater=LayoutInflater.from(mcontext);
            View view=inflater.inflate(R.layout.exampleitem,parent,false);
            return new Examplewviewholder(view);
    }

    @Override
    public void onBindViewHolder(Examplewviewholder examplewviewholder, int i) {
           if(!mcursor.moveToPosition(i))
           {
               return;
           }
           String tittle=mcursor.getString(mcursor.getColumnIndex(emailcontract.emailpasswordentry.COLOUMN_1));
           String desc=mcursor.getString(mcursor.getColumnIndex(emailcontract.emailpasswordentry.COLOUMN_2));
           String name=mcursor.getString(mcursor.getColumnIndex(emailcontract.emailpasswordentry.COLOUMN_3));
           long id=mcursor.getLong(mcursor.getColumnIndex(emailcontract.emailpasswordentry._ID));
           examplewviewholder.mtextview1.setText(tittle);
           examplewviewholder.mtextview2.setText(desc);
           examplewviewholder.mtextview3.setText(name);
           examplewviewholder.itemView.setTag(id);
    }
    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }
    public void swapcursor(Cursor newcursor)
    {
        if(mcursor!=null)
        {
            mcursor.close();
        }
        mcursor=newcursor;
        if(newcursor!=null)
        {
            notifyDataSetChanged();
        }
    }
}

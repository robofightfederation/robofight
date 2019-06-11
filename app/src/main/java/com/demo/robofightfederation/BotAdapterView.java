package com.demo.robofightfederation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.robofightfederation.models.Robot;

import java.util.List;

public class BotAdapterView extends RecyclerView.Adapter<BotAdapterView.BotAdapterHolder> {

    public static class BotAdapterHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView wins;
        private TextView losses;
        private Button scrap;

        public BotAdapterHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_name);
            wins = itemView.findViewById(R.id.text_win);
            losses = itemView.findViewById(R.id.text_loss);
            scrap = itemView.findViewById(R.id.button_scrap);
        }

        public void setBot(Robot robot) {
            name.setText(robot.getCallSign());
            wins.setText(String.format("%d : ", robot.getWins()));
            losses.setText(String.format("%d", robot.getLoses()));

            scrap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private List<Robot> robots;

    public BotAdapterView(List<Robot> robots) {
        this.robots = robots;
    }

    @NonNull
    @Override
    public BotAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.bot_view, parent, false);

        BotAdapterHolder holder = new BotAdapterHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BotAdapterHolder holder, int position) {
        Robot robot = robots.get(position);
        holder.setBot(robot);
    }

    @Override
    public int getItemCount() {
        return robots.size();
    }

    public void addOrUpdateBot(Robot robot) {
        for (int x = 0; x < this.robots.size(); x++) {
            if (this.robots.get(x).getId().equals(robot.getId())) {
                this.robots.set(x, robot);
                this.notifyItemChanged(x);
                return;
            }
        }

        this.robots.add(robot);
        this.notifyItemInserted(this.robots.size() - 1);
    }


}

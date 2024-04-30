package com.lim.sarisarisaya;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapter.InstructionViewAdapter> {
    private List<Instruction> instructions;

    // Constructor to initialize the adapter with a list of instructions
    public InstructionAdapter(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    // Create new views
    @NonNull
    @Override
    public InstructionViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_item, parent, false);

        // Return a new ViewHolder instance with the inflated view
        return new InstructionViewAdapter(view);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull InstructionViewAdapter holder, int position) {
        // Get the instruction object at the specified position
        Instruction instruction = instructions.get(position);

        // Set the text of the TextViews in the ViewHolder with instruction data
        holder.txtInstruction.setText(instruction.getInstructionTitle());
        holder.txtDescription.setText(instruction.getDescription());
    }

    // Return the size of the dataset
    @Override
    public int getItemCount() {
        return instructions.size();
    }

    // ViewHolder class to hold references to the views for each item in the RecyclerView
    public static class InstructionViewAdapter extends RecyclerView.ViewHolder {
        // TextViews to display the instruction title and description
        private TextView txtInstruction, txtDescription;

        // Constructor to initialize the ViewHolder with the item view
        public InstructionViewAdapter(@NonNull View itemView) {
            super(itemView);

            // Find and assign references to the TextViews in the item view
            txtInstruction = itemView.findViewById(R.id.txt_instructionTitle);
            txtDescription = itemView.findViewById(R.id.txt_instructionDescription);
        }
    }
}

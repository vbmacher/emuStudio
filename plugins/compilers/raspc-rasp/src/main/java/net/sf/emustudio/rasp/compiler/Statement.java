/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.emustudio.rasp.compiler;

import net.sf.emustudio.rasp.compiler.CompilerOutput;
import net.sf.emustudio.rasp.compiler.tree.AbstractTreeNode;
import net.sf.emustudio.rasp.memory.memoryitems.NumberMemoryItem;
import net.sf.emustudio.rasp.memory.memoryitems.RASPInstructionImpl;

/**
 *
 * @author miso
 */
public class Statement extends AbstractTreeNode{

    private final RASPInstructionImpl instruction;
    private final Integer operand;
    private final String labelOperand;

    public Statement(RASPInstructionImpl instruction, Integer operand) {
        this.instruction = instruction;
        this.operand = operand;
        this.labelOperand = null;
    }

    public Statement(RASPInstructionImpl instruction, String labelOperand) {
        this.instruction = instruction;
        this.labelOperand = labelOperand.toUpperCase();
        this.operand = null;
    }

    public RASPInstructionImpl getInstruction() {
        return instruction;
    }

    public Integer getOperand() {
        return operand;
    }

    public String getLabelOperand() {
        return labelOperand;
    }

    @Override
    public void pass() throws Exception{
        //add instruction
        CompilerOutput.getInstance().addMemoryItem(instruction);
        if (operand != null) {
            CompilerOutput.getInstance().addMemoryItem(new NumberMemoryItem(operand));
        } else if (labelOperand != null) {
            //operand is label, so we are working with jump instructions
            int address = CompilerOutput.getInstance().getAddressForLabel(labelOperand);
            CompilerOutput.getInstance().addMemoryItem(new NumberMemoryItem(address));
        }
    }

}

// Copyright 2006 Google Inc. All Rights Reserved.

package ca.odell.glazedlists.event;

import ca.odell.glazedlists.EventList;

import java.util.List;

/**
 * Strictly a cut&paste of {@link ca.odell.glazedlists.event.BarcodeListDeltasListEvent}, see {@link ca.odell.glazedlists.event.TreeDeltas}.
 *
 * <p><font color="#FF0000"><strong>Warning: </strong></font> this
 * class is part of an experimental new API.
 *
 * @author <a href="mailto:jesse@swank.ca">Jesse Wilson</a>
 */
class Tree4DeltasListEvent<E> extends ListEvent<E> {

    private Tree4Deltas.Iterator deltasIterator;
    private BlockSequence.Iterator linearIterator;

    private ListEventAssembler.Tree4DeltasAssembler deltasAssembler;

    public Tree4DeltasListEvent(ListEventAssembler.Tree4DeltasAssembler deltasAssembler, EventList<E> sourceList) {
        super(sourceList);
        this.deltasAssembler = deltasAssembler;
    }

    /**
     * Create a copy of this list event.
     */
    public ListEvent copy() {
        Tree4DeltasListEvent<E> result = new Tree4DeltasListEvent<E>(deltasAssembler, sourceList);
        result.deltasIterator = deltasIterator != null ? deltasIterator.copy() : null;
        result.linearIterator = linearIterator != null ? linearIterator.copy() : null;
        result.deltasAssembler = deltasAssembler;
        return result;
    }

    public void reset() {
        // prefer to use the linear blocks, which are faster
        if(deltasAssembler.getUseListBlocksLinear()) {
            this.linearIterator = deltasAssembler.getListBlocksLinear().iterator();
            this.deltasIterator = null;

        // otherwise use the deltas, which are more general
        } else {
            this.deltasIterator = deltasAssembler.getListDeltas().iterator();
            this.linearIterator = null;
        }
    }

    public boolean next() {
        if(linearIterator != null) return linearIterator.next();
        else return deltasIterator.next();
    }

    public boolean hasNext() {
        if(linearIterator != null) return linearIterator.hasNext();
        else return deltasIterator.hasNext();
    }

    public boolean nextBlock() {
        return next();
    }

    public boolean isReordering() {
        return (deltasAssembler.getReorderMap() != null);
    }

    public int[] getReorderMap() {
        int[] reorderMap = deltasAssembler.getReorderMap();
        if(reorderMap == null) throw new IllegalStateException("Cannot get reorder map for a non-reordering change");
        return reorderMap;
    }

    public int getIndex() {
        if(linearIterator != null) return linearIterator.getIndex();
        else return deltasIterator.getIndex();
    }

    public int getBlockStartIndex() {
        return getIndex();
    }

    public int getBlockEndIndex() {
        return getIndex();
    }

    public int getType() {
        if(linearIterator != null) {
            return linearIterator.getType();
        } else {
            return deltasIterator.getType();
        }
    }

    List getBlocks() {
        throw new UnsupportedOperationException();
    }

    public int getBlocksRemaining() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        if(linearIterator != null) {
            return "ListEvent: " + deltasAssembler.getListBlocksLinear().toString();
        } else {
            return "ListEvent: " + deltasAssembler.getListDeltas().toString();
        }
    }
}
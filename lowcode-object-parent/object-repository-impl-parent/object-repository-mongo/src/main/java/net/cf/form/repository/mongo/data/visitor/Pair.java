package net.cf.form.repository.mongo.data.visitor;

public class Pair<S, T> {
    private S left;

    private T right;

    public Pair(S left, T right) {
        this.left = left;
        this.right = right;
    }

    public static <S, T> Pair<S, T> of(S left, T right) {
        return new Pair<>(left, right);
    }

    public S getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }
}

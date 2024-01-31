function func(arr) {
    let a = [1, 2, 3];
    let b = [4, 5, 6];
    let c = [...a, ...b, ...arr];
    return c;
}

func([7, 8, 9]);
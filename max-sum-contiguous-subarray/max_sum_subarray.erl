-module(max_sum_subarray).

-export([run/1]).

run(List) -> run(List, 0).
run([I | List], Sum) -> 
    max(max(Sum, run(List, 0)), max(Sum + I, run(List, Sum + I)));
run([], Sum) -> Sum.
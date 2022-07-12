-module(match).
-export([match/2]).
match(String , Pattern) -> match(String, Pattern, nil).
match([I | String], [I | Pattern], _PreviousPatternChar) -> match(String, Pattern, I);
match([_I | String], [$. | Pattern], _PreviousPatternChar) -> match(String, Pattern, $.);
match([_I | String] = CompleteString, [$* | Pattern] = CompletePattern, $.) -> match(CompleteString, Pattern, $.) or match(String, CompletePattern, $.);
match([I | String] = CompleteString, [$* | Pattern] = CompletePattern, I) -> match(CompleteString, Pattern, I) or match(String, CompletePattern, I);
match(CompleteString, [$* | Pattern], PreviousPatternChar) -> match(CompleteString, Pattern, PreviousPatternChar);
match([], [], _) -> true;
match(_, _, _) -> false.
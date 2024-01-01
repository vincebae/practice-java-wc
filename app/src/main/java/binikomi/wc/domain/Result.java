package binikomi.wc.domain;

public sealed interface Result permits CountResult, ErrorResult {}

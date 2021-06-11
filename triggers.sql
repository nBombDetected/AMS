create definer = root@localhost trigger consumption_record_id
    before insert
    on consumption_record
    for each row
begin
    if new.id = -1 then
        select max(id) from consumption_record into @max_id;

        if @max_id is null then
            set new.id = 1;
        else
            set new.id = @max_id + 1;
        end if;
    end if;
end;

create definer = root@localhost trigger update_inventory_consumption
    after insert
    on consumption_record
    for each row
begin
    if new.valid = 1 then
        select amount from accessory where item_id = new.item_id and valid = 1 into @amount;
        set @amount = @amount - new.amount;
        update accessory set amount = @amount where item_id = new.item_id and valid = 1;
    end if;
end;

create definer = root@localhost trigger issue_record_id
    before insert
    on issue_record
    for each row
begin
    if new.id = -1 then
        select max(id) from issue_record into @max_id;

        if @max_id is null then
            set new.id = 0;
        else
            set new.id = @max_id + 1;
        end if;

    end if;
end;

create definer = root@localhost trigger update_inventory_issue
    after insert
    on issue_record
    for each row
begin
    if new.valid = 1 then
        select amount from accessory where item_id = new.item_id and valid = 1 into @amount;
        set @amount = @amount - new.issue_amount + new.reclaimed_amount;
        update accessory set amount = @amount where item_id = new.item_id and valid = 1;
    end if;
end;

create definer = root@localhost trigger update_inventory_stockin
    after insert
    on stockin_record
    for each row
begin
    if new.valid = 1 then
        select amount from accessory where item_id = new.item_id and valid = 1 into @amount;
        set @amount = @amount + new.amount;
        update accessory set amount = @amount where item_id = new.item_id and valid = 1;
    end if;
end;


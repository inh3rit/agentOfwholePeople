package com.exemple.demo.mapper;

import com.exemple.demo.domain.CreditCard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CreditCardMapper {

    @Insert("insert into credit_card(card_num, bank_name, sub_bank_name, city, is_default, agent_id_num) " +
            "values(#{card_num}, #{bank_name}, #{sub_bank_name}, #{city}, #{is_default}, #{agent_id_num})")
    int insertCreditCard(CreditCard credit_ard);

    @Select("select * from credit_card where agent_id_num = #{agentIdNum}")
    List<CreditCard> getCreditCards(String agentIdNum);

    @Update("update credit_card set is_default=0")
    void updateIsDefault();

    @Select("select count(id) from credit_card where agent_id_num=#{agent_id_num}")
    Integer creditCardCount(@Param("agent_id_num") String agent_id_num);

    @Select("select * from credit_card where agent_id_num=#{agent_id_num} limit #{offset}, #{pageSize}")
    List<CreditCard> creditCardList(@Param("agent_id_num") String agent_id_num, @Param("offset") Integer offset,
                                    @Param("pageSize") Integer pageSize);
}

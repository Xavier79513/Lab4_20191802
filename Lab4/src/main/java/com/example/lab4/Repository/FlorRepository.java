package com.example.lab4.Repository;

import com.example.lab4.Entity.Color;
import com.example.lab4.Entity.Flor;
import com.example.lab4.Entity.Ocasion;
import com.example.lab4.Entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlorRepository extends JpaRepository<Flor, Long> {

    @Query("SELECT f FROM Flor f WHERE " +
            "(:color IS NULL OR f.color = :color) AND " +
            "(:tipo IS NULL OR f.tipo = :tipo) AND " +
            "(:ocasion IS NULL OR f.ocasion = :ocasion)")
    List<Flor> findByFilters(@Param("color") Color color,
                             @Param("tipo") Tipo tipo,
                             @Param("ocasion") Ocasion ocasion);
}
package sss.idao;

import sss.model.Schdule;
import java.util.ArrayList;
public interface ISchdule {
    // 增
    public boolean insert(Schdule schdule);

    // 删(根据id删除）
    public boolean delete(int sched_id);

    // 改
    public boolean update(Schdule schedule);

    // 查所有演出计划(一般用于和界面交互)
    public ArrayList<Schdule> findSchduleAll(int offset, int nums);

    public ArrayList<Schdule> findSchduleByPlayId(int play_id);

    public Schdule findSchduleById(int Schdule_id);

}

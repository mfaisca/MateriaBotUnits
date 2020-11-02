package Unit9;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kuja")
public class Kuja extends Unit{
	public Kuja() {
		super("Kuja");
	}

	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{5503}; 
						break;
					case "JP":
						ids = new int[]{10108}; 
						break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{5400}; 
						break;
					case "JP":
						ids = new int[]{9474}; 
						break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{5403, 5404}; 
						break;
					case "JP":
						ids = new int[]{10104, 10105}; 
						break;
				}
				break;
			case S2:
				ids = new int[]{5408, 5414};
				break;
			case EX:
				ids = new int[]{5399};
				break;
			case AA:
				ids = new int[]{5350};
				break;
			case LD:
				ids = new int[]{11836};
				break;
			case BT:
				ids = new int[]{9473};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}

	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		if(ret.getAilmentById(249) != null)
			ret.getAilmentById(249).setRank(-1);
		switch(ret.getId()) {
			case 5414: //S2
				ret.fixMergeAbility(5418);
				break;
			case 5399: //EX
				ret.getAilmentById(723).getAuras().get(71).ailmentEffect = Ailment.EffectType.E1.getId();
				ret.getAilmentById(723).getAuras().get(71).target = Ailment.Target.Party.getId();
				ret.getAilmentById(723).getAuras().get(74).ailmentEffect = Ailment.EffectType.E3.getId();
				ret.getAilmentById(723).getAuras().get(74).target = Ailment.Target.Party.getId();
				if(ret.getAilmentById(723).getEffects().stream().noneMatch(eg -> eg.fakeDesc != null))
					ret.getAilmentById(723).getEffects().add(new Ailment.EffectGrouping("「**Soul Cleave**」 is not dispelled when using + skills"));
				break;
			case 9472: //LD
			case 11836: //LD
				ret.getAilmentById(1766).setRank(0);
				ret.fixMissingAuraAilment(1766, 766, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1766, 767, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1766, 768, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1766, 1038, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.getAilmentById(1766).getAuras().get(1038).rankData[0] = ret.getAilmentById(1766).getAuras().get(1038).rankData[1];
				break;
			case 9473: //BT
				ret.removeHitDataById(10435);
				ret.removeHitDataById(10436);
				ret.getAilmentById(1765).getAuras().get(765).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1765).getAuras().get(854).ailmentEffect = Ailment.EffectType.E67.getId();
				ret.getAilmentById(1765).getAuras().get(854).target = Ailment.Target.Party.getId();
				break;
		}
		return ret;
	}
}
import java.awt.*;
import java.beans.*;
import symantec.itools.awt.Label3D;
public class KneePointTable extends java.awt.Panel
{
	public KneePointTable()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(15,3,0,0));
		setSize(430,270);
		redtablelabel = new java.awt.Label("RED",Label.CENTER);
		redtablelabel.setBounds(0,0,77,37);
		add(redtablelabel);
		whitetablelabel = new java.awt.Label("WHITE",Label.CENTER);
		whitetablelabel.setBounds(77,0,77,37);
		add(whitetablelabel);
		bluetablelabel = new java.awt.Label("BLUE",Label.CENTER);
		bluetablelabel.setBounds(154,0,77,37);
		add(bluetablelabel);
		tablepanel_aa = new java.awt.Panel();
		tablepanel_aa.setLayout(new GridLayout(1,2,0,0));
		tablepanel_aa.setBounds(0,37,77,37);
		add(tablepanel_aa);
		redtablelabel1 = new java.awt.Label("Volt",Label.CENTER);
		redtablelabel1.setBounds(0,0,38,37);
		tablepanel_aa.add(redtablelabel1);
		reddtablelabel2 = new java.awt.Label("mA",Label.CENTER);
		reddtablelabel2.setBounds(38,0,38,37);
		tablepanel_aa.add(reddtablelabel2);
		tablepanel_ba = new java.awt.Panel();
		tablepanel_ba.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ba.setBounds(77,37,77,37);
		add(tablepanel_ba);
		whitetablelabel1 = new java.awt.Label("Volt",Label.CENTER);
		whitetablelabel1.setBounds(0,0,38,37);
		tablepanel_ba.add(whitetablelabel1);
		whittablelabel2 = new java.awt.Label("mA",Label.CENTER);
		whittablelabel2.setBounds(38,0,38,37);
		tablepanel_ba.add(whittablelabel2);
		tablepanel_ca = new java.awt.Panel();
		tablepanel_ca.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ca.setBounds(154,37,77,37);
		add(tablepanel_ca);
		bluetablelabel1 = new java.awt.Label("Volt",Label.CENTER);
		bluetablelabel1.setBounds(0,0,38,37);
		tablepanel_ca.add(bluetablelabel1);
		bluetablelabel2 = new java.awt.Label("mA",Label.CENTER);
		bluetablelabel2.setBounds(38,0,38,37);
		tablepanel_ca.add(bluetablelabel2);
		tablepanel_ab = new java.awt.Panel();
		tablepanel_ab.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ab.setBounds(0,74,77,37);
		add(tablepanel_ab);
		tablelabel_ab_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ab_v.setBounds(0,0,38,37);
		tablepanel_ab.add(tablelabel_ab_v);
		tablelabel_ab_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ab_a.setBounds(38,0,38,37);
		tablepanel_ab.add(tablelabel_ab_a);
		tablepanel_bb = new java.awt.Panel();
		tablepanel_bb.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bb.setBounds(77,74,77,37);
		add(tablepanel_bb);
		tablelabel_bb_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bb_v.setBounds(0,0,38,37);
		tablepanel_bb.add(tablelabel_bb_v);
		tablelabel_bb_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bb_a.setBounds(38,0,38,37);
		tablepanel_bb.add(tablelabel_bb_a);
		tablepanel_cb = new java.awt.Panel();
		tablepanel_cb.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cb.setBounds(154,74,77,37);
		add(tablepanel_cb);
		tablelabel_cb_v = new java.awt.Label("",Label.CENTER);
		tablelabel_cb_v.setBounds(0,0,38,37);
		tablepanel_cb.add(tablelabel_cb_v);
		tablelabel_cb_a = new java.awt.Label("",Label.CENTER);
		tablelabel_cb_a.setBounds(38,0,38,37);
		tablepanel_cb.add(tablelabel_cb_a);
		tablepanel_ab_i = new java.awt.Panel();
		tablepanel_ab_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ab_i.setBounds(0,111,77,37);
		add(tablepanel_ab_i);
		tablelabel_ab_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ab_iv.setBounds(0,0,38,37);
		tablepanel_ab_i.add(tablelabel_ab_iv);
		tablelabel_ab_ia = new java.awt.Label("",Label.CENTER);
		tablelabel_ab_ia.setBounds(38,0,38,37);
		tablepanel_ab_i.add(tablelabel_ab_ia);
		tablepanel_bb_i = new java.awt.Panel();
		tablepanel_bb_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bb_i.setBounds(77,111,77,37);
		add(tablepanel_bb_i);
		tablelabel_bb_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_bb_iv.setBounds(0,0,38,37);
		tablepanel_bb_i.add(tablelabel_bb_iv);
		tablelabel_bb_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_bb_ic.setBounds(38,0,38,37);
		tablepanel_bb_i.add(tablelabel_bb_ic);
		tablepanel_cb_i = new java.awt.Panel();
		tablepanel_cb_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cb_i.setBounds(154,111,77,37);
		add(tablepanel_cb_i);
		tablelabel_cb_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_cb_iv.setBounds(0,0,38,37);
		tablepanel_cb_i.add(tablelabel_cb_iv);
		tablelabel_cb_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_cb_ic.setBounds(38,0,38,37);
		tablepanel_cb_i.add(tablelabel_cb_ic);
		tablepanel_ac = new java.awt.Panel();
		tablepanel_ac.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ac.setBounds(0,148,77,37);
		add(tablepanel_ac);
		tablelabel_ac_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ac_v.setBounds(0,0,38,37);
		tablepanel_ac.add(tablelabel_ac_v);
		tablelabel_ac_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ac_a.setBounds(38,0,38,37);
		tablepanel_ac.add(tablelabel_ac_a);
		tablepanel_bc = new java.awt.Panel();
		tablepanel_bc.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bc.setBounds(77,148,77,37);
		add(tablepanel_bc);
		tablelabel_bc_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bc_v.setBounds(0,0,38,37);
		tablepanel_bc.add(tablelabel_bc_v);
		tablelabel_bc_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bc_a.setBounds(38,0,38,37);
		tablepanel_bc.add(tablelabel_bc_a);
		tablepanel_cc = new java.awt.Panel();
		tablepanel_cc.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cc.setBounds(154,148,77,37);
		add(tablepanel_cc);
		tablelabel_cc_v = new java.awt.Label("",Label.CENTER);
		tablelabel_cc_v.setBounds(0,0,38,37);
		tablepanel_cc.add(tablelabel_cc_v);
		tablelabel_cc_a = new java.awt.Label("",Label.CENTER);
		tablelabel_cc_a.setBounds(38,0,38,37);
		tablepanel_cc.add(tablelabel_cc_a);
		tablepanel_ac_i = new java.awt.Panel();
		tablepanel_ac_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ac_i.setBounds(0,185,77,37);
		add(tablepanel_ac_i);
		tablelabel_ac_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ac_iv.setBounds(0,0,38,37);
		tablepanel_ac_i.add(tablelabel_ac_iv);
		tablelabel_ac_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_ac_ic.setBounds(38,0,38,37);
		tablepanel_ac_i.add(tablelabel_ac_ic);
		tablepanel_bc_i = new java.awt.Panel();
		tablepanel_bc_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bc_i.setBounds(77,185,77,37);
		add(tablepanel_bc_i);
		tablelabel_bc_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_bc_iv.setBounds(0,0,38,37);
		tablepanel_bc_i.add(tablelabel_bc_iv);
		tablelabel_bc_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_bc_ic.setBounds(38,0,38,37);
		tablepanel_bc_i.add(tablelabel_bc_ic);
		tablepanel_cc_i = new java.awt.Panel();
		tablepanel_cc_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cc_i.setBounds(154,185,77,37);
		add(tablepanel_cc_i);
		tablelabel_cc_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_cc_iv.setBounds(0,0,38,37);
		tablepanel_cc_i.add(tablelabel_cc_iv);
		tablelabel_cc_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_cc_ic.setBounds(38,0,38,37);
		tablepanel_cc_i.add(tablelabel_cc_ic);
		tablepanel_ad = new java.awt.Panel();
		tablepanel_ad.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ad.setBounds(0,222,77,37);
		add(tablepanel_ad);
		tablelabel_ad_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ad_v.setBounds(0,0,38,37);
		tablelabel_ad_v.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_ad.add(tablelabel_ad_v);
		tablelabel_ad_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ad_a.setBounds(38,0,38,37);
		tablelabel_ad_a.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_ad.add(tablelabel_ad_a);
		tablepanel_bd = new java.awt.Panel();
		tablepanel_bd.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bd.setBounds(77,222,77,37);
		add(tablepanel_bd);
		tablelabel_bd_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bd_v.setBounds(0,0,38,37);
		tablelabel_bd_v.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_bd.add(tablelabel_bd_v);
		tablelabel_bd_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bd_a.setBounds(38,0,38,37);
		tablelabel_bd_a.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_bd.add(tablelabel_bd_a);
		tablepanel_cd = new java.awt.Panel();
		tablepanel_cd.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cd.setBounds(154,222,77,37);
		add(tablepanel_cd);
		tablelabel_cd_v = new java.awt.Label("",Label.CENTER);
		tablelabel_cd_v.setBounds(0,0,38,37);
		tablelabel_cd_v.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_cd.add(tablelabel_cd_v);
		tablelabel_cd_a = new java.awt.Label("",Label.CENTER);
		tablelabel_cd_a.setBounds(38,0,38,37);
		tablelabel_cd_a.setFont(new Font("Dialog", Font.PLAIN, 12));
		tablepanel_cd.add(tablelabel_cd_a);
		tablepanel_ad_i = new java.awt.Panel();
		tablepanel_ad_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ad_i.setBounds(0,259,77,37);
		add(tablepanel_ad_i);
		tablelabel_ad_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ad_iv.setBounds(0,0,38,37);
		tablepanel_ad_i.add(tablelabel_ad_iv);
		tablelabel_ad_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_ad_ic.setBounds(38,0,38,37);
		tablepanel_ad_i.add(tablelabel_ad_ic);
		tablepanel_bd_i = new java.awt.Panel();
		tablepanel_bd_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bd_i.setBounds(77,259,77,37);
		add(tablepanel_bd_i);
		tablelabel_bd_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_bd_iv.setBounds(0,0,38,37);
		tablepanel_bd_i.add(tablelabel_bd_iv);
		tablelabel_bd_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_bd_ic.setBounds(38,0,38,37);
		tablepanel_bd_i.add(tablelabel_bd_ic);
		tablepanel_cd_i = new java.awt.Panel();
		tablepanel_cd_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cd_i.setBounds(154,259,77,37);
		add(tablepanel_cd_i);
		tablelabel_cd_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_cd_iv.setBounds(0,0,38,37);
		tablepanel_cd_i.add(tablelabel_cd_iv);
		tablelabel_cd_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_cd_ic.setBounds(38,0,38,37);
		tablepanel_cd_i.add(tablelabel_cd_ic);
		tablepanel_ae = new java.awt.Panel();
		tablepanel_ae.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ae.setBounds(0,296,77,37);
		add(tablepanel_ae);
		tablelabel_ae_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ae_v.setBounds(0,0,38,37);
		tablelabel_ae_v.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_ae.add(tablelabel_ae_v);
		tablelabel_ae_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ae_a.setBounds(38,0,38,37);
		tablelabel_ae_a.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_ae.add(tablelabel_ae_a);
		tablepanel_be = new java.awt.Panel();
		tablepanel_be.setLayout(new GridLayout(1,2,0,0));
		tablepanel_be.setBounds(77,296,77,37);
		add(tablepanel_be);
		tablelabel_be_v = new java.awt.Label("",Label.CENTER);
		tablelabel_be_v.setBounds(0,0,38,37);
		tablelabel_be_v.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_be.add(tablelabel_be_v);
		tablelabel_be_a = new java.awt.Label("",Label.CENTER);
		tablelabel_be_a.setBounds(38,0,38,37);
		tablelabel_be_a.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_be.add(tablelabel_be_a);
		tablepanel_ce = new java.awt.Panel();
		tablepanel_ce.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ce.setBounds(154,296,77,37);
		add(tablepanel_ce);
		tablelabel_ce_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ce_v.setBounds(0,0,38,37);
		tablelabel_ce_v.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_ce.add(tablelabel_ce_v);
		tablelabel_ce_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ce_a.setBounds(38,0,38,37);
		tablelabel_ce_a.setFont(new Font("Dialog", Font.BOLD, 12));
		tablepanel_ce.add(tablelabel_ce_a);
		tablepanel_ae_i = new java.awt.Panel();
		tablepanel_ae_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ae_i.setBounds(0,333,77,37);
		add(tablepanel_ae_i);
		tablelabel_ae_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ae_iv.setBounds(0,0,38,37);
		tablepanel_ae_i.add(tablelabel_ae_iv);
		tablelabel_ae_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_ae_ic.setBounds(38,0,38,37);
		tablepanel_ae_i.add(tablelabel_ae_ic);
		tablepanel_be_i = new java.awt.Panel();
		tablepanel_be_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_be_i.setBounds(77,333,77,37);
		add(tablepanel_be_i);
		tablelabel_be_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_be_iv.setBounds(0,0,38,37);
		tablepanel_be_i.add(tablelabel_be_iv);
		tablelabel_be_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_be_ic.setBounds(38,0,38,37);
		tablepanel_be_i.add(tablelabel_be_ic);
		tablepanel_ce_i = new java.awt.Panel();
		tablepanel_ce_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ce_i.setBounds(154,333,77,37);
		add(tablepanel_ce_i);
		tablelabel_ce_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ce_iv.setBounds(0,0,38,37);
		tablepanel_ce_i.add(tablelabel_ce_iv);
		tablelabel_ce_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_ce_ic.setBounds(38,0,38,37);
		tablepanel_ce_i.add(tablelabel_ce_ic);
		tablepanel_af = new java.awt.Panel();
		tablepanel_af.setLayout(new GridLayout(1,2,0,0));
		tablepanel_af.setBounds(0,370,77,37);
		add(tablepanel_af);
		tablelabel_af_v = new java.awt.Label("",Label.CENTER);
		tablelabel_af_v.setBounds(0,0,38,37);
		tablepanel_af.add(tablelabel_af_v);
		tablelabel_af_a = new java.awt.Label("",Label.CENTER);
		tablelabel_af_a.setBounds(38,0,38,37);
		tablepanel_af.add(tablelabel_af_a);
		tablepanel_bf = new java.awt.Panel();
		tablepanel_bf.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bf.setBounds(77,370,77,37);
		add(tablepanel_bf);
		tablelabel_bf_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bf_v.setBounds(0,0,38,37);
		tablepanel_bf.add(tablelabel_bf_v);
		tablelabel_bf_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bf_a.setBounds(38,0,38,37);
		tablepanel_bf.add(tablelabel_bf_a);
		tablepanel_cf = new java.awt.Panel();
		tablepanel_cf.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cf.setBounds(154,370,77,37);
		add(tablepanel_cf);
		tablelabel_cf_v = new java.awt.Label("",Label.CENTER);
		tablelabel_cf_v.setBounds(0,0,38,37);
		tablepanel_cf.add(tablelabel_cf_v);
		tablelabel_cf_a = new java.awt.Label("",Label.CENTER);
		tablelabel_cf_a.setBounds(38,0,38,37);
		tablepanel_cf.add(tablelabel_cf_a);
		tablepanel_af_i = new java.awt.Panel();
		tablepanel_af_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_af_i.setBounds(0,407,77,37);
		add(tablepanel_af_i);
		tablelabel_af_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_af_iv.setBounds(0,0,38,37);
		tablepanel_af_i.add(tablelabel_af_iv);
		tablelabel_af_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_af_ic.setBounds(38,0,38,37);
		tablepanel_af_i.add(tablelabel_af_ic);
		tablepanel_bf_i = new java.awt.Panel();
		tablepanel_bf_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bf_i.setBounds(77,407,77,37);
		add(tablepanel_bf_i);
		tablelabel_bf_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_bf_iv.setBounds(0,0,38,37);
		tablepanel_bf_i.add(tablelabel_bf_iv);
		tablelabel_bf_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_bf_ic.setBounds(38,0,38,37);
		tablepanel_bf_i.add(tablelabel_bf_ic);
		tablepanel_cf_i = new java.awt.Panel();
		tablepanel_cf_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cf_i.setBounds(154,407,77,37);
		add(tablepanel_cf_i);
		tablelabel_cf_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_cf_iv.setBounds(0,0,38,37);
		tablepanel_cf_i.add(tablelabel_cf_iv);
		tablelabel_cf_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_cf_ic.setBounds(38,0,38,37);
		tablepanel_cf_i.add(tablelabel_cf_ic);
		tablepanel_ag = new java.awt.Panel();
		tablepanel_ag.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ag.setBounds(0,444,77,37);
		add(tablepanel_ag);
		tablelabel_ag_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ag_v.setBounds(0,0,38,37);
		tablepanel_ag.add(tablelabel_ag_v);
		tablelabel_ag_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ag_a.setBounds(38,0,38,37);
		tablepanel_ag.add(tablelabel_ag_a);
		tablepanel_bg = new java.awt.Panel();
		tablepanel_bg.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bg.setBounds(77,444,77,37);
		add(tablepanel_bg);
		tablelabel_bg_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bg_v.setBounds(0,0,38,37);
		tablepanel_bg.add(tablelabel_bg_v);
		tablelabel_bg_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bg_a.setBounds(38,0,38,37);
		tablepanel_bg.add(tablelabel_bg_a);
		tablepanel_cg = new java.awt.Panel();
		tablepanel_cg.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cg.setBounds(154,444,77,37);
		add(tablepanel_cg);
		tablelabel_cg_v = new java.awt.Label("",Label.CENTER);
		tablelabel_cg_v.setBounds(0,0,38,37);
		tablepanel_cg.add(tablelabel_cg_v);
		tablelabel_cg_a = new java.awt.Label("",Label.CENTER);
		tablelabel_cg_a.setBounds(38,0,38,37);
		tablepanel_cg.add(tablelabel_cg_a);
		tablepanel_ag_i = new java.awt.Panel();
		tablepanel_ag_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ag_i.setBounds(0,481,77,37);
		add(tablepanel_ag_i);
		tablelabel_ag_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_ag_iv.setBounds(0,0,38,37);
		tablepanel_ag_i.add(tablelabel_ag_iv);
		tablelabel_ag_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_ag_ic.setBounds(38,0,38,37);
		tablepanel_ag_i.add(tablelabel_ag_ic);
		tablepanel_bg_i = new java.awt.Panel();
		tablepanel_bg_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bg_i.setBounds(77,481,77,37);
		add(tablepanel_bg_i);
		tablelabel_bg_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_bg_iv.setBounds(0,0,38,37);
		tablepanel_bg_i.add(tablelabel_bg_iv);
		tablelabel_bg_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_bg_ic.setBounds(38,0,38,37);
		tablepanel_bg_i.add(tablelabel_bg_ic);
		tablepanel_cg_i = new java.awt.Panel();
		tablepanel_cg_i.setLayout(new GridLayout(1,2,0,0));
		tablepanel_cg_i.setBounds(154,481,77,37);
		add(tablepanel_cg_i);
		tablelabel_cg_iv = new java.awt.Label("",Label.CENTER);
		tablelabel_cg_iv.setBounds(0,0,38,37);
		tablepanel_cg_i.add(tablelabel_cg_iv);
		tablelabel_cg_ic = new java.awt.Label("",Label.CENTER);
		tablelabel_cg_ic.setBounds(38,0,38,37);
		tablepanel_cg_i.add(tablelabel_cg_ic);
		tablepanel_ah = new java.awt.Panel();
		tablepanel_ah.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ah.setBounds(0,518,77,37);
		add(tablepanel_ah);
		tablelabel_ah_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ah_v.setBounds(0,0,38,37);
		tablepanel_ah.add(tablelabel_ah_v);
		tablelabel_ah_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ah_a.setBounds(38,0,38,37);
		tablepanel_ah.add(tablelabel_ah_a);
		tablepanel_bh = new java.awt.Panel();
		tablepanel_bh.setLayout(new GridLayout(1,2,0,0));
		tablepanel_bh.setBounds(77,518,77,37);
		add(tablepanel_bh);
		tablelabel_bh_v = new java.awt.Label("",Label.CENTER);
		tablelabel_bh_v.setBounds(0,0,38,37);
		tablepanel_bh.add(tablelabel_bh_v);
		tablelabel_bh_a = new java.awt.Label("",Label.CENTER);
		tablelabel_bh_a.setBounds(38,0,38,37);
		tablepanel_bh.add(tablelabel_bh_a);
		tablepanel_ch = new java.awt.Panel();
		tablepanel_ch.setLayout(new GridLayout(1,2,0,0));
		tablepanel_ch.setBounds(154,518,77,37);
		add(tablepanel_ch);
		tablelabel_ch_v = new java.awt.Label("",Label.CENTER);
		tablelabel_ch_v.setBounds(0,0,38,37);
		tablepanel_ch.add(tablelabel_ch_v);
		tablelabel_ch_a = new java.awt.Label("",Label.CENTER);
		tablelabel_ch_a.setBounds(38,0,38,37);
		tablepanel_ch.add(tablelabel_ch_a);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Label redtablelabel;
	java.awt.Label whitetablelabel;
	java.awt.Label bluetablelabel;
	java.awt.Panel tablepanel_aa;
	java.awt.Label redtablelabel1;
	java.awt.Label reddtablelabel2;
	java.awt.Panel tablepanel_ba;
	java.awt.Label whitetablelabel1;
	java.awt.Label whittablelabel2;
	java.awt.Panel tablepanel_ca;
	java.awt.Label bluetablelabel1;
	java.awt.Label bluetablelabel2;
	java.awt.Panel tablepanel_ab;
	java.awt.Label tablelabel_ab_v;
	java.awt.Label tablelabel_ab_a;
	java.awt.Panel tablepanel_bb;
	java.awt.Label tablelabel_bb_v;
	java.awt.Label tablelabel_bb_a;
	java.awt.Panel tablepanel_cb;
	java.awt.Label tablelabel_cb_v;
	java.awt.Label tablelabel_cb_a;
	java.awt.Panel tablepanel_ab_i;
	java.awt.Label tablelabel_ab_iv;
	java.awt.Label tablelabel_ab_ia;
	java.awt.Panel tablepanel_bb_i;
	java.awt.Label tablelabel_bb_iv;
	java.awt.Label tablelabel_bb_ic;
	java.awt.Panel tablepanel_cb_i;
	java.awt.Label tablelabel_cb_iv;
	java.awt.Label tablelabel_cb_ic;
	java.awt.Panel tablepanel_ac;
	java.awt.Label tablelabel_ac_v;
	java.awt.Label tablelabel_ac_a;
	java.awt.Panel tablepanel_bc;
	java.awt.Label tablelabel_bc_v;
	java.awt.Label tablelabel_bc_a;
	java.awt.Panel tablepanel_cc;
	java.awt.Label tablelabel_cc_v;
	java.awt.Label tablelabel_cc_a;
	java.awt.Panel tablepanel_ac_i;
	java.awt.Label tablelabel_ac_iv;
	java.awt.Label tablelabel_ac_ic;
	java.awt.Panel tablepanel_bc_i;
	java.awt.Label tablelabel_bc_iv;
	java.awt.Label tablelabel_bc_ic;
	java.awt.Panel tablepanel_cc_i;
	java.awt.Label tablelabel_cc_iv;
	java.awt.Label tablelabel_cc_ic;
	java.awt.Panel tablepanel_ad;
	java.awt.Label tablelabel_ad_v;
	java.awt.Label tablelabel_ad_a;
	java.awt.Panel tablepanel_bd;
	java.awt.Label tablelabel_bd_v;
	java.awt.Label tablelabel_bd_a;
	java.awt.Panel tablepanel_cd;
	java.awt.Label tablelabel_cd_v;
	java.awt.Label tablelabel_cd_a;
	java.awt.Panel tablepanel_ad_i;
	java.awt.Label tablelabel_ad_iv;
	java.awt.Label tablelabel_ad_ic;
	java.awt.Panel tablepanel_bd_i;
	java.awt.Label tablelabel_bd_iv;
	java.awt.Label tablelabel_bd_ic;
	java.awt.Panel tablepanel_cd_i;
	java.awt.Label tablelabel_cd_iv;
	java.awt.Label tablelabel_cd_ic;
	java.awt.Panel tablepanel_ae;
	java.awt.Label tablelabel_ae_v;
	java.awt.Label tablelabel_ae_a;
	java.awt.Panel tablepanel_be;
	java.awt.Label tablelabel_be_v;
	java.awt.Label tablelabel_be_a;
	java.awt.Panel tablepanel_ce;
	java.awt.Label tablelabel_ce_v;
	java.awt.Label tablelabel_ce_a;
	java.awt.Panel tablepanel_ae_i;
	java.awt.Label tablelabel_ae_iv;
	java.awt.Label tablelabel_ae_ic;
	java.awt.Panel tablepanel_be_i;
	java.awt.Label tablelabel_be_iv;
	java.awt.Label tablelabel_be_ic;
	java.awt.Panel tablepanel_ce_i;
	java.awt.Label tablelabel_ce_iv;
	java.awt.Label tablelabel_ce_ic;
	java.awt.Panel tablepanel_af;
	java.awt.Label tablelabel_af_v;
	java.awt.Label tablelabel_af_a;
	java.awt.Panel tablepanel_bf;
	java.awt.Label tablelabel_bf_v;
	java.awt.Label tablelabel_bf_a;
	java.awt.Panel tablepanel_cf;
	java.awt.Label tablelabel_cf_v;
	java.awt.Label tablelabel_cf_a;
	java.awt.Panel tablepanel_af_i;
	java.awt.Label tablelabel_af_iv;
	java.awt.Label tablelabel_af_ic;
	java.awt.Panel tablepanel_bf_i;
	java.awt.Label tablelabel_bf_iv;
	java.awt.Label tablelabel_bf_ic;
	java.awt.Panel tablepanel_cf_i;
	java.awt.Label tablelabel_cf_iv;
	java.awt.Label tablelabel_cf_ic;
	java.awt.Panel tablepanel_ag;
	java.awt.Label tablelabel_ag_v;
	java.awt.Label tablelabel_ag_a;
	java.awt.Panel tablepanel_bg;
	java.awt.Label tablelabel_bg_v;
	java.awt.Label tablelabel_bg_a;
	java.awt.Panel tablepanel_cg;
	java.awt.Label tablelabel_cg_v;
	java.awt.Label tablelabel_cg_a;
	java.awt.Panel tablepanel_ag_i;
	java.awt.Label tablelabel_ag_iv;
	java.awt.Label tablelabel_ag_ic;
	java.awt.Panel tablepanel_bg_i;
	java.awt.Label tablelabel_bg_iv;
	java.awt.Label tablelabel_bg_ic;
	java.awt.Panel tablepanel_cg_i;
	java.awt.Label tablelabel_cg_iv;
	java.awt.Label tablelabel_cg_ic;
	java.awt.Panel tablepanel_ah;
	java.awt.Label tablelabel_ah_v;
	java.awt.Label tablelabel_ah_a;
	java.awt.Panel tablepanel_bh;
	java.awt.Label tablelabel_bh_v;
	java.awt.Label tablelabel_bh_a;
	java.awt.Panel tablepanel_ch;
	java.awt.Label tablelabel_ch_v;
	java.awt.Label tablelabel_ch_a;
	//}}

}
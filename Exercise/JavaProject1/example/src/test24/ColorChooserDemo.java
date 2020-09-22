package test24;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColorChooserDemo extends JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;
    protected JColorChooser tcc;
    protected JLabel banner;

    public ColorChooserDemo() {
        super(new BorderLayout());

        //设置一个标签，做广告的。也用来显示用户选择的颜色。
        banner = new JLabel("欢迎使用颜色选择器！", JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("广告"));

        //设置选择颜色选择器
        tcc = new JColorChooser(banner.getForeground()); // 设置初始颜色
        tcc.getSelectionModel().addChangeListener(this); // 给所有模式添加监听
        tcc.setBorder(BorderFactory.createTitledBorder("选择颜色"));

        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
        
    }
}
